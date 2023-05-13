package com.db.polling.domain.service.impl;

import com.db.polling.api.dto.AssociateDTO;
import com.db.polling.api.dto.response.AssociateResponse;
import com.db.polling.api.dto.response.AssociateWrapperResponse;
import com.db.polling.database.entity.AssociateEntity;
import com.db.polling.database.repository.AssociateRepository;
import com.db.polling.domain.exception.NotFoundException;
import com.db.polling.domain.exception.UnprocessableException;
import com.db.polling.domain.mapper.AssociateMapper;
import com.db.polling.domain.service.AssociateService;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AssociateServiceImpl implements AssociateService {

  @Autowired
  private AssociateMapper mapper;

  @Autowired
  private AssociateRepository repository;

  @Override
  @Transactional
  public AssociateDTO createAssociate(AssociateDTO associateDTO) {

    Optional<AssociateEntity> associateEntity = repository.findByCpf(associateDTO.getCpf());

    if (associateEntity.isPresent()) {
      throw new UnprocessableException("VOTE-002");
    }

    AssociateEntity associateEntitySaved = repository.save(mapper.toEntity(associateDTO));

    return mapper.toDTO(associateEntitySaved);
  }

  @Override
  public AssociateResponse getAssociateById(Long associateId) {
    return mapper.toDTOResponse(getAssociate(associateId));
  }

  @Override
  public AssociateWrapperResponse getAllAssociates(Integer page, Integer size) {
    PageRequest pageable = PageRequest.of(page, size);

    Page<AssociateEntity> associateDTOS = repository.findAll(pageable);

    return buildAssociateResponse(associateDTOS);
  }

  private AssociateEntity getAssociate(Long associateId) {
    return repository.findById(associateId)
        .orElseThrow(() -> new NotFoundException("VOTE-001"));
  }

  private AssociateWrapperResponse buildAssociateResponse(Page<AssociateEntity> associateEntities) {

    AssociateWrapperResponse associateResponseDTO = new AssociateWrapperResponse();

    List<AssociateResponse> associateDTOList = associateEntities.get().map(mapper::toDTOResponse)
        .toList();

    associateResponseDTO.setAssociateGetResponses(associateDTOList);
    associateResponseDTO.setHasNext(associateEntities.hasNext());
    associateResponseDTO.setTotalElements((int) associateEntities.getTotalElements());

    return associateResponseDTO;

  }
}
