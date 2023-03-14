package com.dbserver.service;


import com.dbserver.exception.BusinessException;
import com.dbserver.exception.EntityNotFoundException;
import com.dbserver.model.dto.AgendaCreateDTO;
import com.dbserver.model.dto.AgendaDTO;
import com.dbserver.model.entity.Agenda;
import com.dbserver.mapper.AgendaMapper;
import com.dbserver.repository.AgendaRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.catchThrowableOfType;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
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

    @Test
    void shouldCreateAgenda() {
        AgendaCreateDTO agendaCreateDTO = getAgendaCreateDTOMock();
        Agenda agenda = getAgendaMock();
        AgendaDTO agendaDTO = getAgendaDTOMock();
        when(agendaMapper.toEntity(agendaCreateDTO)).thenReturn(agenda);
        when(agendaMapper.toDTO(agenda)).thenReturn(agendaDTO);
        when(agendaRepository.save(agenda)).thenReturn(agenda);
        AgendaDTO saved = agendaService.create(agendaCreateDTO);
        assertThat(saved, equalTo(agendaDTO));
    }

    @Test
    void shouldThrowBusinessException() {
        AgendaCreateDTO agendaCreateDTO = getAgendaCreateDTOMock();
        Agenda agenda = getAgendaMock();
        AgendaDTO agendaDTO = getAgendaDTOMock();
        when(agendaMapper.toEntity(agendaCreateDTO)).thenReturn(agenda);
        when(agendaRepository.save(agenda)).thenThrow(new RuntimeException("Erro de teste"));
        BusinessException throwable = catchThrowableOfType(() -> agendaService.create(agendaCreateDTO), BusinessException.class);
        assertThat(throwable.getClass(), equalTo(BusinessException.class));
    }


    @Test
    void shouldGetAgendaById() {
        Agenda agenda = getAgendaMock();
        when(agendaRepository.findById(agenda.getId())).thenReturn(Optional.of(agenda));
        Agenda foundAgenda = agendaService.findById(agenda.getId());
        assertThat(foundAgenda, equalTo(agenda));
    }

    @Test
    void shouldThrowEntityNotFoundException() {
        Agenda agenda = getAgendaMock();
        when(agendaRepository.findById(agenda.getId())).thenReturn(Optional.empty());
        EntityNotFoundException throwable =
                catchThrowableOfType(() -> agendaService.findById(agenda.getId()), EntityNotFoundException.class);
        assertThat(throwable.getClass(), equalTo(EntityNotFoundException.class));
        assertThat(throwable.getReason(), containsString("Agenda not found"));
        assertThat(throwable.getStatusCode(), equalTo(NOT_FOUND));
    }

    @Test
    void shouldGetAgendaDTOById() {
        Agenda agenda = getAgendaMock();
        AgendaDTO agendaDTO = getAgendaDTOMock();
        when(agendaRepository.findById(agenda.getId())).thenReturn(Optional.of(agenda));
        when(agendaMapper.toDTO(agenda)).thenReturn(agendaDTO);
        AgendaDTO foundAgenda = agendaService.getAgendaById(agenda.getId());
        assertThat(foundAgenda, equalTo(agendaDTO));
    }

    @Test
    void shouldGetAll() {
        Agenda agenda = getAgendaMock();
        AgendaDTO agendaDTO = getAgendaDTOMock();
        when(agendaRepository.findAll()).thenReturn(Arrays.asList(agenda));
        when(agendaMapper.toDTO(agenda)).thenReturn(agendaDTO);
        List<AgendaDTO> agendas = agendaService.getAll();
        assertThat(agendas.size(), equalTo(1));
    }


    private AgendaCreateDTO getAgendaCreateDTOMock() {
        return AgendaCreateDTO.builder()
                .title("teste")
                .description("teste")
                .build();
    }

    private Agenda getAgendaMock() {
        return Agenda.builder()
                .id("6404ff797f24ce45b0022c83")
                .title("teste")
                .description("teste")
                .createdDate(LocalDateTime.now())
                .build();
    }

    private AgendaDTO getAgendaDTOMock() {
        return AgendaDTO.builder()
                .id(getAgendaMock().getId())
                .createdDate(getAgendaMock().getCreatedDate())
                .title("teste")
                .description("teste")
                .build();
    }

}
