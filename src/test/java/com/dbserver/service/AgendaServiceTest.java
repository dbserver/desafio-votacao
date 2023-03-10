package com.dbserver.service;


import com.dbserver.exception.BusinessException;
import com.dbserver.exception.EntityNotFoundException;
import com.dbserver.model.dto.AgendaCreateDTO;
import com.dbserver.model.dto.AgendaDTO;
import com.dbserver.model.entity.Agenda;
import com.dbserver.model.mapper.AgendaMapper;
import com.dbserver.repository.AgendaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.catchThrowableOfType;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.mockito.Mockito.when;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@ExtendWith(MockitoExtension.class)
class AgendaServiceTest {

    @Mock
    private AgendaRepository agendaRepository;
    @Mock
    private AgendaMapper agendaMapper;

    @InjectMocks
    private AgendaService agendaService;

    private Agenda agenda;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
        agenda = Agenda.builder()
                .id("6404ff797f24ce45b0022c83")
                .title("titulo")
                .description("descrição")
                .createdDate(LocalDateTime.now())
                .build();
    }

    @Test
    void shouldCreateAgenda() {
        AgendaCreateDTO agendaCreateDTO = AgendaCreateDTO.builder().title("teste").description("teste").build();
        Agenda agenda = Agenda.builder().title("teste").description("teste").build();
        AgendaDTO agendaDTO = AgendaDTO.builder().title("teste").description("teste").build();
        when(agendaMapper.toEntity(agendaCreateDTO)).thenReturn(agenda);
        when(agendaMapper.toDTO(agenda)).thenReturn(agendaDTO);
        when(agendaRepository.save(agenda)).thenReturn(agenda);
        AgendaDTO saved = agendaService.create(agendaCreateDTO);
        assertThat(saved, equalTo(agendaDTO));
    }

    @Test
    void shouldThrowBusinessException() {
        AgendaCreateDTO agendaCreateDTO = AgendaCreateDTO.builder().title("teste").description("teste").build();
        Agenda agenda = Agenda.builder().title("teste").description("teste").build();
        AgendaDTO agendaDTO = AgendaDTO.builder().title("teste").description("teste").build();
        when(agendaMapper.toEntity(agendaCreateDTO)).thenReturn(agenda);
        when(agendaRepository.save(agenda)).thenThrow(RuntimeException.class);
        BusinessException throwable = catchThrowableOfType(() -> agendaService.create(agendaCreateDTO), BusinessException.class);
        assertThat(throwable.getClass(), equalTo(BusinessException.class));
    }

    @Test
    void shouldGetAgendaById() {
        AgendaDTO agendaDTO = AgendaDTO.builder().build();
        when(agendaRepository.findById(agenda.getId())).thenReturn(Optional.of(agenda));
        Agenda foundAgenda = agendaService.findById(agenda.getId());
        assertThat(foundAgenda, equalTo(agenda));
    }

    @Test
    void shouldThrowEntityNotFoundException() {
        AgendaDTO agendaDTO = AgendaDTO.builder().build();
        when(agendaRepository.findById(agenda.getId())).thenReturn(Optional.empty());
        EntityNotFoundException throwable =
                catchThrowableOfType(() -> agendaService.findById(agenda.getId()), EntityNotFoundException.class);
        assertThat(throwable.getClass(), equalTo(EntityNotFoundException.class));
        assertThat(throwable.getReason(), equalTo("Agenda not found"));
        assertThat(throwable.getStatusCode(), equalTo(NOT_FOUND));
    }

    @Test
    void shouldGetAgendaDTOById() {
        AgendaDTO agendaDTO = AgendaDTO.builder().build();
        when(agendaRepository.findById(agenda.getId())).thenReturn(Optional.of(agenda));
        when(agendaMapper.toDTO(agenda)).thenReturn(agendaDTO);
        AgendaDTO foundAgenda = agendaService.getAgendaDTOById(agenda.getId());
        assertThat(foundAgenda, equalTo(agendaDTO));
    }

    @Test
    void shouldGetAll() {
        AgendaDTO agendaDTO = AgendaDTO.builder().build();
        when(agendaRepository.findAll()).thenReturn(Arrays.asList(agenda));
        when(agendaMapper.toDTO(agenda)).thenReturn(agendaDTO);
        List<AgendaDTO> agendas = agendaService.getAll();
        assertThat(agendas.size(), equalTo(1));
    }
}
