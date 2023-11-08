package com.example.desafiovotacao.service.implementations;

import com.example.desafiovotacao.dto.*;
import com.example.desafiovotacao.entity.RulingEntity;
import com.example.desafiovotacao.entity.SessionEntity;
import com.example.desafiovotacao.exception.ValidationExceptions;
import com.example.desafiovotacao.exception.enums.implementations.InformationErrorMessages;
import com.example.desafiovotacao.exception.enums.implementations.RulingErrorMessages;
import com.example.desafiovotacao.exception.enums.implementations.SessionErrorMessages;
import com.example.desafiovotacao.repository.RulingRepository;
import com.example.desafiovotacao.service.interfaces.RulingService;
import com.example.desafiovotacao.utils.DateUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static java.util.Objects.*;

@Service
@RequiredArgsConstructor
public class RulingServiceImpl implements RulingService {

    private final RulingRepository rulingRepository;
    private final SessionServiceImpl sessionService;

    @Override
    public CreatedRulingDTO create(RegisterRulingDTO ruling) {
        validateRegisterRulingDTO(ruling);

        RulingEntity newRuling = rulingRepository.save(
                RulingEntity.builder()
                        .title(ruling.getTitle())
                        .description(ruling.getDescription())
                        .build()
        );

        return CreatedRulingDTO.builder()
                .rulingId(newRuling.getId())
                .title(newRuling.getTitle())
                .description(newRuling.getDescription())
                .build();
    }

    @Override
    public CountingResultsDTO countVotes(Integer rulingId) {
        RulingEntity existingRuling = getRulingEntityIfExists(rulingId);
        if(nonNull(existingRuling.getResults())) {
            throw new ValidationExceptions(RulingErrorMessages.RULING_HAS_ALREADY_ENDED);
        }

        SessionEntity existingSession = sessionService.getSessionByRulingId(existingRuling.getId());
        if(existingSession.isSessionRunning()){
            throw new ValidationExceptions(SessionErrorMessages.SESSION_IS_STILL_RUNNING);
        }

        VotesDTO countedVotes = rulingRepository.countVotes(rulingId);
        if(Objects.equals(countedVotes.getVotesInFavor(), countedVotes.getVotesAgainst())){
            throw new ValidationExceptions(RulingErrorMessages.VOTING_TIE);
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
        return rulingRepository.findById(rulingId).orElseThrow(() -> {
            throw new ValidationExceptions(RulingErrorMessages.RULING_DOES_NOT_EXIST);
        });
    }

    @Override
    public RulingReturnDTO getRulingReturnIfExists(Integer rulingId) {
        return rulingRepository.listReturnById(rulingId).orElseThrow(() -> {
            throw new ValidationExceptions(RulingErrorMessages.RULING_DOES_NOT_EXIST);
        });
    }

    @Override
    public void validateRegisterRulingDTO(RegisterRulingDTO registerRulingDTO) {
        if(registerRulingDTO.getTitle() == null || registerRulingDTO.getDescription() == null) {
            throw new ValidationExceptions(InformationErrorMessages.FAULTY_INFORMATION);
        }
    }

}