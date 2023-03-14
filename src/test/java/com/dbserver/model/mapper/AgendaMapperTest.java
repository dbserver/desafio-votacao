package com.dbserver.model.mapper;

import com.dbserver.model.dto.AgendaDTO;
import com.dbserver.model.dto.AgendaCreateDTO;
import com.dbserver.model.entity.Agenda;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.time.LocalDateTime;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

@SpringBootTest
@TestPropertySource(locations = "classpath:test.properties")
class AgendaMapperTest {

    @Autowired
    private AgendaMapper agendaMapper;

    @Test
    void shouldMapperCreateDTOToEntity() {
        AgendaCreateDTO requestDTO = AgendaCreateDTO.builder().title("teste").description("teste").build();
        Agenda agenda = agendaMapper.toEntity(requestDTO);
        Agenda toCompare = Agenda.builder().title(requestDTO.getTitle()).description(requestDTO.getDescription()).build();
        assertThat(toCompare, equalTo(agenda));
    }

    @Test
    void shouldMapperEntityToDTO() {
        Agenda agenda = Agenda.builder().title("teste").description("teste").id("id01").createdDate(LocalDateTime.now()).build();
        AgendaDTO agendaDTO = agendaMapper.toDTO(agenda);
        AgendaDTO toCompare = AgendaDTO.builder()
                .id(agenda.getId())
                .createdDate(agenda.getCreatedDate())
                .title(agenda.getTitle())
                .description(agenda.getDescription())
                .build();
        assertThat(toCompare, equalTo(agendaDTO));
    }

}
