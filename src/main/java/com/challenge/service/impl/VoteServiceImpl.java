package com.challenge.service.impl;

import com.challenge.dto.VoteRequestDto;
import com.challenge.model.Associate;
import com.challenge.model.StaveSession;
import com.challenge.model.Vote;
import com.challenge.repository.AssociateRepository;
import com.challenge.repository.StaveSessionRepository;
import com.challenge.repository.VoteRepository;
import com.challenge.service.VoteService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.challenge.enums.SessionStatusEnum.CLOSE;

@Service
@AllArgsConstructor
public class VoteServiceImpl implements VoteService {

    private final Logger logger = LoggerFactory.getLogger(VoteServiceImpl.class);
    private final VoteRepository voteRepository;
    private final StaveSessionRepository staveSessionRepository;
    private final AssociateRepository associateRepository;

    @Override
    public Vote save(VoteRequestDto request) {
        final Associate associate = associateRepository.getReferenceById(request.getAssociateId());

        if (voteRepository.existsByAssociateAndSession(associate.getId(), request.getStaveSessionId())) {
            throw new IllegalArgumentException("Associado já votou para essa sessão.");
        }

        final StaveSession staveSession = staveSessionRepository.getReferenceById(request.getStaveSessionId());

        if (CLOSE == staveSession.getStatus()) {
            throw new IllegalArgumentException("Sessão fechada para voto");
        }

        logger.info("Salvando voto do associado {} para a pauta {} (sessao: {})", associate.getName(), staveSession.getStave().getTitle(), staveSession.getId());
        Vote vote = Vote.builder()
                .vote(request.getVote())
                .associate(associate)
                .staveSession(staveSession)
                .build();

        return voteRepository.save(vote);
    }
}
