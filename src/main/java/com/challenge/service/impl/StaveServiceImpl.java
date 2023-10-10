package com.challenge.service.impl;

import com.challenge.dto.StaveRequestDto;
import com.challenge.dto.StaveSessionRequestDto;
import com.challenge.dto.VoteCountResponseDto;
import com.challenge.model.Stave;
import com.challenge.model.StaveSession;
import com.challenge.model.Vote;
import com.challenge.repository.StaveRepository;
import com.challenge.repository.StaveSessionRepository;
import com.challenge.repository.VoteRepository;
import com.challenge.service.StaveService;
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
    private static final Integer DEFAULT_DURATION_SESSION_IN_MINUTES = 1;
    private static final Long MILLISECONDS_CONVERTER = 60000L;
    @Override
    public Stave create(StaveRequestDto request) {
        return staveRepository.save(Stave.builder().title(request.getTitle()).build());
    }

    @Override
    public StaveSession session(Long staveId, StaveSessionRequestDto request) {
        final StaveSession session = staveSessionRepository.findByStaveId(staveId);
        return Optional.ofNullable(session).orElseGet(() -> openSession(staveId, request));
    }

    @Override
    public VoteCountResponseDto countVotes(Long staveId) {
        final Stave stave = staveRepository.getReferenceById(staveId);
        final StaveSession session = staveSessionRepository.findByStaveId(staveId);

        if (OPEN == session.getStatus()){
            throw new IllegalArgumentException("Sessão está aberta para votação!");
        }

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
        final Integer duration = (request.getDuration() > 0 && request.getDuration() < 60) ? request.getDuration() : DEFAULT_DURATION_SESSION_IN_MINUTES;
        final StaveSession openSession = staveSessionRepository.save(StaveSession.builder()
                .duration(duration)
                .status(OPEN)
                .stave(stave)
                .build());
        this.scheduleCloseSession(openSession);

        return openSession;
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
}
