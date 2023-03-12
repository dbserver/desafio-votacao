package com.dbserver.model.mapper;

import com.dbserver.model.dto.VotingSessionCreateDTO;
import com.dbserver.model.dto.VotingSessionDTO;
import com.dbserver.model.entity.VotingSession;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Optional;

@Component
public class VotingSessionMapper {

    @Autowired
    private ObjectMapper objectMapper;

    public VotingSession toEntity(VotingSessionCreateDTO votingSessionCreateDTO) {
        VotingSession voting = objectMapper.convertValue(votingSessionCreateDTO, VotingSession.class);
        Long duration = Optional.ofNullable(voting.getDuration()).orElse(60000L);
        voting.setDuration(duration);
        voting.setStartDate(LocalDateTime.now());
        voting.setEndDate(LocalDateTime.now().plus(duration, ChronoUnit.MILLIS));
        return voting;
    }

    public VotingSessionDTO toDTO(VotingSession voting) {
        return objectMapper.convertValue(voting, VotingSessionDTO.class);
    }

}
