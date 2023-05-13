package com.db.polling.domain.service.impl;

import com.db.polling.api.dto.AgendaDTO;
import com.db.polling.api.dto.response.AgendaResponse;
import com.db.polling.api.dto.response.AgendaWrapperResponse;
import com.db.polling.database.entity.AgendaEntity;
import com.db.polling.database.repository.AgendaRepository;
import com.db.polling.domain.exception.NotFoundException;
import com.db.polling.domain.exception.UnprocessableException;
import com.db.polling.domain.mapper.AgendaMapper;
import com.db.polling.domain.service.AgendaService;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AgendaServiceImpl implements AgendaService {

  @Autowired
  private AgendaMapper mapper;

  @Autowired
  private AgendaRepository repository;


  @Override
  @Transactional
  public AgendaDTO createAgenda(AgendaDTO agendaDTO) {
    Optional<AgendaEntity> associateEntity = repository.findByTitle(agendaDTO.getTitle());

    if (associateEntity.isPresent()) {
      throw new UnprocessableException("VOTE-004");
    }

    AgendaEntity associateEntitySaved = repository.save(mapper.toEntity(agendaDTO));

    return mapper.toDTO(associateEntitySaved);
  }

  @Override
  public AgendaResponse getAgendaById(Long agendaId) {
    return mapper.toDTOResponse(getAgenda(agendaId));
  }

  @Override
  public AgendaWrapperResponse getAllAgendas(Integer page, Integer size) {
    PageRequest pageable = PageRequest.of(page, size);

    Page<AgendaEntity> associateDTOS = repository.findAll(pageable);

    return buildAgendaResponse(associateDTOS);
  }

  private AgendaEntity getAgenda(Long agendaId) {
    return repository.findById(agendaId)
        .orElseThrow(() -> new NotFoundException("VOTE-003"));
  }

  private AgendaWrapperResponse buildAgendaResponse(Page<AgendaEntity> agendaEntities) {

    AgendaWrapperResponse associateResponseDTO = new AgendaWrapperResponse();

    List<AgendaResponse> agendaResponses = agendaEntities.get().map(mapper::toDTOResponse)
        .toList();

    associateResponseDTO.setAgendaResponses(agendaResponses);
    associateResponseDTO.setHasNext(agendaEntities.hasNext());
    associateResponseDTO.setTotalElements((int) agendaEntities.getTotalElements());

    return associateResponseDTO;

  }
}
