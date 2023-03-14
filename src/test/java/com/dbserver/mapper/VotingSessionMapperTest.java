package com.dbserver.mapper;

import com.dbserver.mapper.VotingSessionMapper;
import com.dbserver.model.dto.VotingSessionCreateDTO;
import com.dbserver.model.dto.VotingSessionDTO;
import com.dbserver.model.entity.VotingSession;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.time.LocalDateTime;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

@SpringBootTest
@TestPropertySource(locations = "classpath:test.properties")
class VotingSessionMapperTest {

    @Autowired
    private VotingSessionMapper votingSessionMapper;

    @Test
    void shouldMapperCreateDTOToEntity() {
        VotingSessionCreateDTO votingSessionCreateDTO = VotingSessionCreateDTO.builder().duration(1000L).idAgenda("idAgenda").build();
        VotingSession voting = votingSessionMapper.toEntity(votingSessionCreateDTO);
        VotingSession toCompare = VotingSession.builder()
                .idAgenda(votingSessionCreateDTO.getIdAgenda())
                .duration(votingSessionCreateDTO.getDuration())
                .startDate(voting.getStartDate())
                .endDate(voting.getEndDate())
                .build();
        assertThat(toCompare, equalTo(voting));
    }

    @Test
    void shouldMapperCreateDTOToEntityWithDefaultTime() {
        VotingSessionCreateDTO votingSessionCreateDTO = VotingSessionCreateDTO.builder().idAgenda("idAgenda").build();
        VotingSession voting = votingSessionMapper.toEntity(votingSessionCreateDTO);
        VotingSession toCompare = VotingSession.builder()
                .idAgenda(votingSessionCreateDTO.getIdAgenda())
                .duration(60000L)
                .startDate(voting.getStartDate())
                .endDate(voting.getEndDate())
                .build();
        assertThat(toCompare, equalTo(voting));
    }

    @Test
    void shouldMapperEntityToDTO() {
        LocalDateTime date = LocalDateTime.now();
        VotingSession voting = VotingSession.builder()
                .id("01")
                .duration(6000l)
                .startDate(date)
                .endDate(date)
                .idAgenda("idAgenda")
                .build();
        VotingSessionDTO votingSessionDTO = votingSessionMapper.toDTO(voting);
        VotingSessionDTO toCompare = VotingSessionDTO.builder()
                .id("01")
                .duration(6000l)
                .startDate(date)
                .endDate(date)
                .idAgenda("idAgenda")
                .build();
        assertThat(toCompare, equalTo(votingSessionDTO));
    }

}
