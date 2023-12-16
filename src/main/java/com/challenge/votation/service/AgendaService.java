package com.challenge.votation.service;

import com.challenge.votation.exception.VotationException;
import com.challenge.votation.exception.VotationNotFoundException;
import com.challenge.votation.mapper.AgendaMapper;
import com.challenge.votation.model.*;
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

    public AgendaCreateResponse createAgenda(AgendaCreateRequest agendaCreateRequest) {
        AgendaEntity agendaEntity = new AgendaEntity();
        agendaEntity.setName(agendaCreateRequest.getAgendaName());

        AgendaEntity agendaCreated = agendaRepository.save(agendaEntity);
        log.info("Agenda has been created: {}", agendaCreated);

        return agendaMapper.mapAgendaCreateResponse(agendaCreated);
    }

    public AgendaOpenResponse openAgenda(Long agendaId, AgendaOpenRequest agendaOpenRequest) {
        AgendaEntity agendaEntity = findAgendaById(agendaId);
        if (agendaEntity.getStartDate() != null || agendaEntity.getEndDate() != null) {
            throw new VotationException("Agenda is already opened.");
        }
        openAgenda(agendaEntity, agendaOpenRequest);
        log.info("Agenda has been opened: {}", agendaOpenRequest);

        return agendaMapper.mapAgendaOpenResponse(agendaEntity);
    }

    private AgendaEntity findAgendaById(Long agendaId) {
        log.info("Finding Agenda By Id: {}", agendaId);
        Optional<AgendaEntity> optAgendaEntity = agendaRepository.findById(agendaId);
        if (optAgendaEntity.isEmpty()) {
            throw new VotationNotFoundException("Agenda was not found.");
        }
        return optAgendaEntity.get();
    }

    private void openAgenda(AgendaEntity agendaEntity, AgendaOpenRequest agendaOpenRequest) {
        if (agendaOpenRequest.getAgendaEnd() == null) {
            agendaOpenRequest.setAgendaEnd(LocalDateTime.now().plusMinutes(1));
        } else {
            if (agendaOpenRequest.getAgendaEnd().isBefore(LocalDateTime.now())) {
                throw new VotationException("The Agenda End Date informed is in the past.");
            }
        }

        agendaEntity.setStartDate(LocalDateTime.now());
        agendaEntity.setEndDate(agendaOpenRequest.getAgendaEnd());
        agendaRepository.save(agendaEntity);
    }

    public void vote(Long agendaId, AgendaVoteRequest agendaVoteRequest) {
        AgendaEntity agendaEntity = findAgendaById(agendaId);
        if (agendaEntity.getStartDate() == null || agendaEntity.getEndDate() == null) {
            throw new VotationException("Agenda it's not open yet.");
        }
        LocalDateTime now = LocalDateTime.now();
        if (now.isAfter(agendaEntity.getEndDate())) {
            throw new VotationException("Agenda it's already closed.");
        }

        voteService.saveVote(agendaEntity, agendaVoteRequest);
    }

    public AgendaResponse getAgendaStatus(Long agendaId) {
        AgendaEntity agendaEntity = findAgendaById(agendaId);
        return getAgendaData(agendaEntity);
    }

    private AgendaResponse getAgendaData(AgendaEntity agendaEntity) {
        log.info("Getting Agenda Data: {}", agendaEntity.getId());
        AgendaResponse agendaResponse = agendaMapper.mapAgendaResponse(agendaEntity);

        if (agendaEntity.getEndDate() == null) {
            agendaResponse.setAgendaStatus(AgendaStatus.NOT_STARTED);
        } else if (LocalDateTime.now().isAfter(agendaEntity.getEndDate())) {
            agendaResponse.setAgendaStatus(AgendaStatus.FINISHED);
        } else {
            agendaResponse.setAgendaStatus(AgendaStatus.IN_PROGRESS);
        }

        setAgendaVotes(agendaEntity, agendaResponse);

        return agendaResponse;
    }

    private void setAgendaVotes(AgendaEntity agendaEntity, AgendaResponse agendaResponse) {
        log.info("Setting Agenda Votes");
        Set<VoteEntity> votes = agendaEntity.getVotes();
        long totalVotes = votes.size();
        long yesVotes = votes.stream().filter(VoteEntity::isVote).count();
        long noVotes = votes.stream().filter(vote -> !vote.isVote()).count();
        agendaResponse.setTotalVotes(totalVotes);
        agendaResponse.setYesVotes(yesVotes);
        agendaResponse.setNoVotes(noVotes);

        if (yesVotes == noVotes) {
            agendaResponse.setAgendaResult(null);
            agendaResponse.setAgendaResultDescription("It's a tie, the number of votes are equal");
        } else if (yesVotes > noVotes) {
            agendaResponse.setAgendaResult(true);
            agendaResponse.setAgendaResultDescription("YES is the most votaded");
        } else {
            agendaResponse.setAgendaResult(false);
            agendaResponse.setAgendaResultDescription("NO is the most votaded");
        }
    }

    public Page<AgendaResponse> getAgendasStatus(Pageable pageable) {
        Page<AgendaEntity> entities = agendaRepository.findAll(pageable);

        return entities.map(this::getAgendaData);
    }
}
