package com.dbserver.mapper;


import com.dbserver.mapper.VoteMapper;
import com.dbserver.model.dto.VoteCreatedDTO;
import com.dbserver.model.dto.VoteDTO;
import com.dbserver.model.entity.Vote;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

@SpringBootTest
@TestPropertySource(locations = "classpath:test.properties")
class VoteMapperTest {

    @Autowired
    private VoteMapper voteMapper;

    @Test
    void shouldMapperCreateDTOToEntity() {
        VoteCreatedDTO votingCreateDTO = VoteCreatedDTO.builder()
                .vote(true)
                .idAgenda("idAgenda")
                .cpf("cpf")
                .build();
        Vote vote = voteMapper.toEntity(votingCreateDTO);
        Vote toCompare = Vote.builder()
                .vote(true)
                .idAgenda("idAgenda")
                .cpf("cpf")
                .build();
        assertThat(toCompare, equalTo(vote));
    }

    @Test
    void shouldMapperEntityToDTO() {
        Vote vote = Vote.builder()
                .vote(true)
                .idAgenda("idAgenda")
                .cpf("cpf")
                .build();
        VoteDTO voteDTO = voteMapper.toDTO(vote);
        VoteDTO toCompare = VoteDTO.builder()
                .vote(vote.getVote())
                .idAgenda(voteDTO.getIdAgenda())
                .cpf(vote.getCpf())
                .build();
        assertThat(toCompare, equalTo(voteDTO));
    }

}
