package com.challenge.votation.service;

import com.challenge.votation.exception.VotationException;
import com.challenge.votation.exception.VotationNotFoundException;
import com.challenge.votation.mapper.AgendaMapper;
import com.challenge.votation.model.Agenda;
import com.challenge.votation.model.AgendaStatus;
import com.challenge.votation.model.Vote;
import com.challenge.votation.repository.AgendaRepository;
import com.challenge.votation.repository.entity.AgendaEntity;
import com.challenge.votation.repository.entity.VoteEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
@Log4j2
public class AgendaService {
    private final AgendaRepository agendaRepository;
    private final AgendaMapper agendaMapper;

    private final VoteService voteService;

    public Agenda createAgenda(Agenda agenda) {
        AgendaEntity agendaEntity = new AgendaEntity();
        agendaEntity.setName(agenda.getAgendaName());

        AgendaEntity agendaCreated = agendaRepository.save(agendaEntity);
        log.info("Agenda has been created: {}", agendaCreated);

        return agendaMapper.map(agendaCreated);
    }

    public Agenda openAgenda(Long agendaId, Agenda agenda) {
        AgendaEntity agendaEntity = findAgendaById(agendaId);
        if (agendaEntity.getStartDate() != null || agendaEntity.getEndDate() != null) {
            throw new VotationException("Agenda is already opened.");
        }
        openAgenda(agendaEntity, agenda);
        log.info("Agenda has been opened: {}", agenda);

        return agendaMapper.map(agendaEntity);
    }

    private AgendaEntity findAgendaById(Long agendaId) {
        log.info("Finding Agenda By Id: {}", agendaId);
        Optional<AgendaEntity> optAgendaEntity = agendaRepository.findById(agendaId);
        if (optAgendaEntity.isEmpty()) {
            throw new VotationNotFoundException("Agenda was not found.");
        }
        return optAgendaEntity.get();
    }

    private void openAgenda(AgendaEntity agendaEntity, Agenda agenda) {
        if (agenda.getAgendaEnd() == null) {
            agenda.setAgendaEnd(LocalDateTime.now().plusMinutes(1));
        } else {
            if (agenda.getAgendaEnd().isBefore(LocalDateTime.now())) {
                throw new VotationException("The Agenda End Date informed is in the past.");
            }
        }

        agendaEntity.setStartDate(LocalDateTime.now());
        agendaEntity.setEndDate(agenda.getAgendaEnd());
        agendaRepository.save(agendaEntity);
    }

    public void vote(Long agendaId, Vote vote) {
        AgendaEntity agendaEntity = findAgendaById(agendaId);
        if (agendaEntity.getStartDate() == null || agendaEntity.getEndDate() == null) {
            throw new VotationException("Agenda it's not open yet.");
        }
        LocalDateTime now = LocalDateTime.now();
        if (now.isAfter(agendaEntity.getEndDate())) {
            throw new VotationException("Agenda it's already closed.");
        }

        voteService.saveVote(agendaEntity, vote);
    }

    public Agenda getAgendaStatus(Long agendaId) {
        AgendaEntity agendaEntity = findAgendaById(agendaId);
        return getAgendaData(agendaEntity);
    }

    private Agenda getAgendaData(AgendaEntity agendaEntity) {
        log.info("Getting Agenda Data: {}", agendaEntity.getId());
        Agenda agenda = agendaMapper.map(agendaEntity);

        if (agendaEntity.getEndDate() == null) {
            agenda.setAgendaStatus(AgendaStatus.NOT_STARTED);
        } else if (LocalDateTime.now().isAfter(agendaEntity.getEndDate())) {
            agenda.setAgendaStatus(AgendaStatus.FINISHED);
        } else {
            agenda.setAgendaStatus(AgendaStatus.IN_PROGRESS);
        }

        setAgendaVotes(agendaEntity, agenda);

        return agenda;
    }

    private void setAgendaVotes(AgendaEntity agendaEntity, Agenda agenda) {
        log.info("Setting Agenda Votes");
        Set<VoteEntity> votes = agendaEntity.getVotes();
        long totalVotes = votes.size();
        long yesVotes = votes.stream().filter(VoteEntity::isVote).count();
        long noVotes = votes.stream().filter(vote -> !vote.isVote()).count();
        agenda.setTotalVotes(totalVotes);
        agenda.setYesVotes(yesVotes);
        agenda.setNoVotes(noVotes);

        if (yesVotes == noVotes) {
            agenda.setAgendaResult(null);
            agenda.setAgendaResultDescription("It's a tie, the number of votes are equal");
        } else if (yesVotes > noVotes) {
            agenda.setAgendaResult(true);
            agenda.setAgendaResultDescription("YES is the most votaded");
        } else {
            agenda.setAgendaResult(false);
            agenda.setAgendaResultDescription("NO is the most votaded");
        }
    }

    public Page<Agenda> getAgendasStatus(Pageable pageable) {
        Page<AgendaEntity> entities = agendaRepository.findAll(pageable);

        return entities.map(this::getAgendaData);
    }
}
