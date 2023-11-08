package com.example.desafiovotacao.service.implementations;

import com.example.desafiovotacao.dto.*;
import com.example.desafiovotacao.entity.RulingEntity;
import com.example.desafiovotacao.entity.SessionEntity;
import com.example.desafiovotacao.exception.RulingExceptions;
import com.example.desafiovotacao.exception.SessionExceptions;
import com.example.desafiovotacao.exception.ValidationExceptions;
import com.example.desafiovotacao.repository.RulingRepository;
import com.example.desafiovotacao.service.interfaces.RulingService;
import com.example.desafiovotacao.service.interfaces.SessionService;
import com.example.desafiovotacao.utils.DateUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RulingServiceImpl implements RulingService {

    private final RulingRepository rulingRepository;
    private final SessionServiceImpl sessionService;

    @Override
    public CreatedRulingDTO create(RegisterRulingDTO ruling) {
        validateRegisterRulingDTO(ruling);

        RulingEntity newRuling = new RulingEntity();
        newRuling.setTitle(ruling.getTitle());
        newRuling.setDescription(ruling.getDescription());
        newRuling = rulingRepository.save(newRuling);

        return CreatedRulingDTO.builder()
                .rulingId(newRuling.getId())
                .title(newRuling.getTitle())
                .description(newRuling.getDescription())
                .build();
    }

    @Override
    public CountingResultsDTO countVotes(Integer rulingId) {
        RulingEntity existingRuling = getRulingEntityIfExists(rulingId);
        if(existingRuling.getResults() != null) {
            RulingExceptions.rulingHasAlreadyEnded();
        }

        SessionEntity existingSession = sessionService.getSessionByRulingId(existingRuling.getId());
        if(existingSession.isSessionRunning()){
            SessionExceptions.sessionStillRunning();
        }

        VotesDTO countedVotes = rulingRepository.countVotes(rulingId);
        if(countedVotes.getVotesInFavor() == countedVotes.getVotesAgainst()){
            RulingExceptions.rulingHasVotesTied();
        }

        existingRuling.setResults(countedVotes.getVotesInFavor() > countedVotes.getVotesAgainst());
        existingRuling.setVoteCountDate(new Date());
        existingRuling = rulingRepository.save(existingRuling);

        return CountingResultsDTO.builder()
                .title(existingRuling.getTitle())
                .description(existingRuling.getDescription())
                .result(existingRuling.getResults() ? "Sim" : "Não")
                .inFavorVotes(countedVotes.getVotesInFavor())
                .againstVotes(countedVotes.getVotesAgainst())
                .countDate(DateUtils.formatDate(existingRuling.getVoteCountDate()))
                .sessionDate(DateUtils.formatDate(existingSession.getCreationDate()))
                .creationDate(DateUtils.formatDate(existingRuling.getCreationDate()))
                .build();
    }

    @Override
    public List<RulingReturnDTO> listAll() {
        return rulingRepository.listAllReturn();
    }

    @Override
    public RulingEntity getRulingEntityIfExists(Integer rulingId) {
        Optional<RulingEntity> existingRuling = rulingRepository.findById(rulingId);
        if(existingRuling.isEmpty()){
            RulingExceptions.rulingDoesNotExist();
        }

        return existingRuling.get();
    }

    @Override
    public RulingReturnDTO getRulingReturnIfExists(Integer rulingId) {
        Optional<RulingReturnDTO> existingRuling = rulingRepository.listReturnById(rulingId);
        if(existingRuling.isEmpty()){
            RulingExceptions.rulingDoesNotExist();
        }

        return existingRuling.get();
    }

    @Override
    public void validateRegisterRulingDTO(RegisterRulingDTO registerRulingDTO) {
        if(registerRulingDTO.getTitle() == null || registerRulingDTO.getDescription() == null) {
            ValidationExceptions.faultyInformation();
        }
    }

}