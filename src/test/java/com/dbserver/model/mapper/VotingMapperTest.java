package com.dbserver.model.mapper;

import com.dbserver.model.dto.VotingCreateDTO;
import com.dbserver.model.dto.VotingDTO;
import com.dbserver.model.entity.Voting;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

@SpringBootTest
class VotingMapperTest {

    @Autowired
    private VotingMapper votingMapper;

    @Test
    void shouldMapperCreateDTOToEntity() {
        VotingCreateDTO votingCreateDTO = VotingCreateDTO.builder().duration(6000L).idAgenda("idAgenda").build();
        Voting voting = votingMapper.toEntity(votingCreateDTO);
        Voting toCompare = Voting.builder()
                .idAgenda(votingCreateDTO.getIdAgenda())
                .duration(votingCreateDTO.getDuration())
                .startDate(voting.getStartDate())
                .endDate(voting.getEndDate())
                .build();
        assertThat(toCompare, equalTo(voting));
    }

    @Test
    void shouldMapperEntityToDTO() {
        LocalDateTime date = LocalDateTime.now();
        Voting voting = Voting.builder()
                .id("01")
                .duration(6000l)
                .startDate(date)
                .endDate(date)
                .idAgenda("idAgenda")
                .build();
        VotingDTO votingDTO = votingMapper.toDTO(voting);
        VotingDTO toCompare = VotingDTO.builder()
                .id("01")
                .duration(6000l)
                .startDate(date)
                .endDate(date)
                .idAgenda("idAgenda")
                .build();
        assertThat(toCompare, equalTo(votingDTO));
    }

}
