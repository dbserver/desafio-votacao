package com.db.polling.domain.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.db.polling.api.dto.AgendaDTO;
import com.db.polling.api.dto.response.AgendaResponse;
import com.db.polling.api.dto.response.AgendaWrapperResponse;
import com.db.polling.database.entity.AgendaEntity;
import com.db.polling.database.repository.AgendaRepository;
import com.db.polling.domain.exception.NotFoundException;
import com.db.polling.domain.exception.UnprocessableException;
import com.db.polling.domain.mapper.AgendaMapper;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

@ExtendWith(MockitoExtension.class)
public class AgendaServiceImplTest {

  @InjectMocks
  private AgendaServiceImpl service;

  @Mock
  private AgendaMapper mapper;

  @Mock
  private AgendaRepository repository;

  @Test
  void shouldCreateAgendaWithExistingTitle() {

    AgendaDTO agendaDTO = new AgendaDTO();
    agendaDTO.setTitle("existing title");

    when(repository.findByTitle(agendaDTO.getTitle()))
        .thenReturn(Optional.of(new AgendaEntity()));

    assertThrows(UnprocessableException.class, () -> service.createAgenda(agendaDTO));
  }

  @Test
  void shouldCreateAgendaWithNonExistingTitle() {

    AgendaDTO agendaDTO = new AgendaDTO();
    agendaDTO.setTitle("non existing title");

    when(repository.findByTitle(agendaDTO.getTitle()))
        .thenReturn(Optional.empty());

    AgendaEntity savedEntity = new AgendaEntity();
    savedEntity.setTitle("non existing title");

    when(mapper.toEntity(agendaDTO))
        .thenReturn(savedEntity);

    when(repository.save(savedEntity))
        .thenReturn(savedEntity);

    when(mapper.toDTO(savedEntity))
        .thenReturn(agendaDTO);

    AgendaDTO createdAgendaDTO = service.createAgenda(agendaDTO);

    assertEquals(agendaDTO.getTitle(), createdAgendaDTO.getTitle());
  }

  @Test
  void shouldGetAgendaByIdWithExistingId() {

    Long agendaId = 1L;
    AgendaEntity agendaEntity = new AgendaEntity();
    agendaEntity.setAgendaId(agendaId);

    when(repository.findById(agendaId))
        .thenReturn(Optional.of(agendaEntity));

    AgendaResponse expectedResponse = new AgendaResponse();
    expectedResponse.setAgendaId(agendaId);
    when(mapper.toDTOResponse(agendaEntity))
        .thenReturn(expectedResponse);

    AgendaResponse response = service.getAgendaById(agendaId);

    assertEquals(expectedResponse, response);
  }

  @Test
  void shouldGetAgendaByIdWithNonExistingId() {
    Long agendaId = 1L;

    when(repository.findById(agendaId))
        .thenReturn(Optional.empty());

    assertThrows(NotFoundException.class, () -> service.getAgendaById(agendaId));
  }

  @Test
  void shouldGetAllAgendasWithEmptyList() {

    when(repository.findAll(PageRequest.of(0, 10))).thenReturn(Page.empty());

    AgendaWrapperResponse response = service.getAllAgendas(0, 10);

    assertNotNull(response);
    assertEquals(0, response.getAgendaResponses().size());
    assertFalse(response.isHasNext());
    assertEquals(0, response.getTotalElements());
    verify(repository).findAll(PageRequest.of(0, 10));
  }


}
