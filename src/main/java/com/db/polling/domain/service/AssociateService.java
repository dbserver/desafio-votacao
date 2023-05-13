package com.db.polling.domain.service;

import com.db.polling.api.dto.AssociateDTO;

import com.db.polling.api.dto.response.AssociateResponse;
import com.db.polling.api.dto.response.AssociateWrapperResponse;

public interface AssociateService {

  AssociateDTO createAssociate(AssociateDTO createAssociateDTO);

  AssociateResponse getAssociateById(Long id);

  AssociateWrapperResponse getAllAssociates(Integer page, Integer size);
}
