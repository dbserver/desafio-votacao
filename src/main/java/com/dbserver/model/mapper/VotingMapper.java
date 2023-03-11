package com.dbserver.model.mapper;

import com.dbserver.model.dto.VotingCreateDTO;
import com.dbserver.model.dto.VotingDTO;
import com.dbserver.model.entity.Voting;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Optional;

@Component
public class VotingMapper {

    @Autowired
    private ObjectMapper objectMapper;

    public Voting toEntity(VotingCreateDTO votingCreateDTO) {
        Voting voting = objectMapper.convertValue(votingCreateDTO, Voting.class);
        Long duration = Optional.ofNullable(voting.getDuration()).orElse(60000L);
        voting.setDuration(duration);
        voting.setStartDate(LocalDateTime.now());
        voting.setEndDate(LocalDateTime.now().plus(duration, ChronoUnit.MILLIS));
        return voting;
    }

    public VotingDTO toDTO(Voting voting) {
        return objectMapper.convertValue(voting, VotingDTO.class);
    }

}
