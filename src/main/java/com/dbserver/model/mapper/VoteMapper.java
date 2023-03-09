package com.dbserver.model.mapper;

import com.dbserver.model.dto.VoteCreatedDTO;
import com.dbserver.model.dto.VoteDTO;
import com.dbserver.model.entity.Vote;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class VoteMapper {

    @Autowired
    private ObjectMapper objectMapper;

    public Vote toEntity(VoteCreatedDTO voteCreatedDTO) {
        return objectMapper.convertValue(voteCreatedDTO, Vote.class);
    }

    public VoteDTO toDTO(Vote vote) {
        return objectMapper.convertValue(vote, VoteDTO.class);
    }
}
