package com.db.polling.domain.service.impl;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import com.db.polling.api.dto.AssociateDTO;
import com.db.polling.api.dto.response.AssociateResponse;
import com.db.polling.api.dto.response.AssociateWrapperResponse;
import com.db.polling.database.entity.AssociateEntity;
import com.db.polling.database.repository.AssociateRepository;
import com.db.polling.domain.exception.NotFoundException;
import com.db.polling.domain.exception.UnprocessableException;
import com.db.polling.domain.mapper.AssociateMapper;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

@ExtendWith(MockitoExtension.class)
public class AssociateServiceImplTest {

  @InjectMocks
  private AssociateServiceImpl associateService;

  @Mock
  private AssociateMapper mapper;

  @Mock
  private AssociateRepository repository;

  @Test
  void shouldThrowUnprocessableExceptionWhenCpfAlreadyExist() {

    AssociateDTO associateDTO = new AssociateDTO();
    associateDTO.setCpf("11111111111");
    AssociateEntity associateEntity = new AssociateEntity();
    associateEntity.setCpf("11111111111");
    when(repository.findByCpf(associateDTO.getCpf())).thenReturn(Optional.of(associateEntity));

    UnprocessableException exception = assertThrows(UnprocessableException.class, () -> associateService.createAssociate(associateDTO));

    assertEquals("VOTE-002", exception.getCode());
    verify(repository).findByCpf(associateDTO.getCpf());
    verifyNoMoreInteractions(repository);
    verifyNoInteractions(mapper);
  }

  @Test
  void shouldCreateCreateAssociateWithSuccess() {

    AssociateDTO associateDTO = new AssociateDTO();
    associateDTO.setCpf("11111111111");
    AssociateEntity associateEntity = new AssociateEntity();
    associateEntity.setCpf("11111111111");
    when(repository.findByCpf(associateDTO.getCpf())).thenReturn(Optional.empty());
    when(mapper.toEntity(associateDTO)).thenReturn(associateEntity);
    when(repository.save(associateEntity)).thenReturn(associateEntity);
    when(mapper.toDTO(associateEntity)).thenReturn(associateDTO);

    AssociateDTO createdAssociateDTO = associateService.createAssociate(associateDTO);

    assertEquals(associateDTO, createdAssociateDTO);
    verify(repository).findByCpf(associateDTO.getCpf());
    verify(repository).save(associateEntity);
    verify(mapper).toEntity(associateDTO);
    verify(mapper).toDTO(associateEntity);
    verifyNoMoreInteractions(repository, mapper);
  }

  @Test
  void shouldGetAssociateByIdWithSuccess() {
    Long associateId = 1L;
    AssociateEntity associateEntity = new AssociateEntity();
    AssociateResponse associateResponse = new AssociateResponse();
    when(repository.findById(associateId)).thenReturn(Optional.of(associateEntity));
    when(mapper.toDTOResponse(associateEntity)).thenReturn(associateResponse);

    AssociateResponse foundAssociateResponse = associateService.getAssociateById(associateId);

    assertEquals(associateResponse, foundAssociateResponse);
    verify(repository).findById(associateId);
    verify(mapper).toDTOResponse(associateEntity);
    verifyNoMoreInteractions(repository, mapper);
  }

  @Test
  void shouldGetAssociateByIdThenThrowNotFoundException() {
    Long associateId = 1L;
    when(repository.findById(associateId)).thenReturn(Optional.empty());

    NotFoundException exception = assertThrows(NotFoundException.class, () -> associateService.getAssociateById(associateId));

    assertEquals("VOTE-001", exception.getCode());
    verify(repository).findById(associateId);
    verifyNoMoreInteractions(repository, mapper);
  }

  @Test
  void shouldGetAllAssociates() {
    Page<AssociateEntity> page = new PageImpl<>(List.of(new AssociateEntity(), new AssociateEntity()));
    given(repository.findAll(PageRequest.of(0, 10))).willReturn(page);

    AssociateWrapperResponse response = associateService.getAllAssociates(0, 10);

    assertEquals(2, response.getAssociateGetResponses().size());
    assertFalse(response.isHasNext());
    assertEquals(2, response.getTotalElements());
  }

}
