package com.challenge.votation.service;

import com.challenge.votation.exception.VotationException;
import com.challenge.votation.model.Vote;
import com.challenge.votation.repository.VoteRepository;
import com.challenge.votation.repository.entity.AgendaEntity;
import com.challenge.votation.repository.entity.VoteEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Log4j2
public class VoteService {
    private final VoteRepository voteRepository;

    public void saveVote(AgendaEntity agendaEntity, Vote vote) {
        log.info("Saving Vote: {} to Agenda: {}", vote, agendaEntity);
        boolean voteExists = voteRepository.existsByClientIdAndAgendaEntity(vote.getClientId(), agendaEntity);

        if (voteExists) {
            throw new VotationException("This client already voted for this agenda.");
        }

        VoteEntity voteEntity = new VoteEntity();
        voteEntity.setClientId(vote.getClientId());
        voteEntity.setVote(vote.getClientVote());
        voteEntity.setAgendaEntity(agendaEntity);

        voteRepository.save(voteEntity);
    }
}
