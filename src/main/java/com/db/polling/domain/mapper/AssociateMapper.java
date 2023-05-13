package com.db.polling.domain.mapper;

import com.db.polling.api.dto.AssociateDTO;
import com.db.polling.api.dto.response.AssociateResponse;
import com.db.polling.database.entity.AssociateEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AssociateMapper {

  AssociateEntity toEntity(AssociateDTO associateDTO);

  AssociateDTO toDTO(AssociateEntity associateEntity);

  AssociateResponse toDTOResponse(AssociateEntity associateEntity);

}
