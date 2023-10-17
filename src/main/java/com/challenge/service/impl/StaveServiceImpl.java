package com.challenge.service.impl;

import com.challenge.dto.StaveRequestDto;
import com.challenge.dto.StaveSessionRequestDto;
import com.challenge.dto.VoteCountResponseDto;
import com.challenge.exceptions.StaveException;
import com.challenge.model.Stave;
import com.challenge.model.StaveSession;
import com.challenge.model.Vote;
import com.challenge.repository.StaveRepository;
import com.challenge.repository.StaveSessionRepository;
import com.challenge.repository.VoteRepository;
import com.challenge.service.StaveService;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Timer;
import java.util.TimerTask;

import static com.challenge.enums.SessionStatusEnum.CLOSE;
import static com.challenge.enums.SessionStatusEnum.OPEN;
import static com.challenge.enums.VoteEnum.NO;
import static com.challenge.enums.VoteEnum.YES;

@Service
@AllArgsConstructor
public class StaveServiceImpl implements StaveService {

    private final Logger logger = LoggerFactory.getLogger(StaveServiceImpl.class);
    private final StaveRepository staveRepository;
    private final StaveSessionRepository staveSessionRepository;
    private final VoteRepository voteRepository;
    private static final Long MILLISECONDS_CONVERTER = 60000L;

    @Override
    public Stave create(StaveRequestDto request) {
        logger.info("Criando pauta titulo: {}", request.getTitle());
        return staveRepository.save(Stave.builder().title(request.getTitle()).build());
    }

    @Override
    public StaveSession session(Long staveId, StaveSessionRequestDto request) {
        final StaveSession session = staveSessionRepository.findByStaveId(staveId).orElse(openSession(staveId, request));

        logger.info("Sessao: {}", session);
        if (OPEN == session.getStatus()) {
            this.scheduleCloseSession(session);
        }

        return session;
    }

    @Override
    public VoteCountResponseDto countVotes(Long staveId) {
        final Stave stave = staveRepository.getReferenceById(staveId);
        final StaveSession session = staveSessionRepository.findByStaveId(staveId).orElseThrow(EntityNotFoundException::new);

        this.ifSessionOpenThrowException(session);

        logger.info("Contabilizando votos para pauta {} (sessao: {})", stave.getTitle(), session.getId());

        final List<Vote> votes = voteRepository.findByStaveSession(session);
        final Long yesVotes = votes.stream().filter(vote -> vote.getVote() == YES).count();
        final Long noVotes = votes.stream().filter(vote -> vote.getVote() == NO).count();

        return VoteCountResponseDto.builder()
                .staveTitle(stave.getTitle())
                .total(votes.size())
                .yesVotes(yesVotes)
                .noVotes(noVotes).build();
    }

    private StaveSession openSession(Long staveId, StaveSessionRequestDto request) {
        final Stave stave = staveRepository.getReferenceById(staveId);
        logger.info("Abrindo nova sessão para pauta {} com duracao {} minutos", stave.getTitle(), request);
        return staveSessionRepository.save(StaveSession.builder()
                .duration(request.getDuration())
                .status(OPEN)
                .stave(stave)
                .build());
    }

    private void scheduleCloseSession(StaveSession session) {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                logger.info("fechando a sessão: {} da pauta: {}", session.getId(), session.getStave().getTitle());
                session.setStatus(CLOSE);
                staveSessionRepository.save(session);
            }
        }, session.getDuration() * MILLISECONDS_CONVERTER);
    }

    private void ifSessionOpenThrowException(StaveSession session) {
        if (OPEN == session.getStatus()) {
            logger.info("Votos nao podem ser computados no momento para pauta {} (sessionId={}) ainda aberta para votacao", session.getStave().getTitle(), session.getId());
            StaveException.sessionStillOpen();
        }
    }
}
