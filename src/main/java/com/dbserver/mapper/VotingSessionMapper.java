package com.dbserver.mapper;

import com.dbserver.exception.BusinessException;
import com.dbserver.model.dto.VotingSessionCreateDTO;
import com.dbserver.model.dto.VotingSessionDTO;
import com.dbserver.model.entity.VotingSession;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Optional;

@Component
public class VotingSessionMapper {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private ObjectMapper objectMapper;

    public VotingSession toEntity(VotingSessionCreateDTO votingSessionCreateDTO) {
        try {
            VotingSession voting = objectMapper.convertValue(votingSessionCreateDTO, VotingSession.class);
            Long duration = Optional.ofNullable(voting.getDuration()).orElse(60000L);
            voting.setDuration(duration);
            voting.setStartDate(LocalDateTime.now());
            voting.setEndDate(LocalDateTime.now().plus(duration, ChronoUnit.MILLIS));
            return voting;
        } catch (RuntimeException e) {
            logger.error("Error mapping voting session", e);
            throw new BusinessException();
        }
    }

    public VotingSessionDTO toDTO(VotingSession voting) {
        try {
            return objectMapper.convertValue(voting, VotingSessionDTO.class);
        } catch (RuntimeException e) {
            logger.error("Error mapping voting session", e);
            throw new BusinessException();
        }
    }

}
