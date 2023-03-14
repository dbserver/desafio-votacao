package com.dbserver.mapper;

import com.dbserver.exception.BusinessException;
import com.dbserver.model.dto.VoteCreatedDTO;
import com.dbserver.model.dto.VoteDTO;
import com.dbserver.model.entity.Vote;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class VoteMapper {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private ObjectMapper objectMapper;

    public Vote toEntity(VoteCreatedDTO voteCreatedDTO) {
        try {
            return objectMapper.convertValue(voteCreatedDTO, Vote.class);
        } catch (RuntimeException e) {
            logger.error("Error mapping vote", e);
            throw new BusinessException();
        }
    }

    public VoteDTO toDTO(Vote vote) {
        try {
            return objectMapper.convertValue(vote, VoteDTO.class);
        } catch (RuntimeException e) {
            logger.error("Error mapping vote", e);
            throw new BusinessException();
        }
    }

}
