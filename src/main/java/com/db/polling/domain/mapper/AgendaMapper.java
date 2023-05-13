package com.db.polling.domain.mapper;

import com.db.polling.api.dto.AgendaDTO;
import com.db.polling.api.dto.response.AgendaResponse;
import com.db.polling.api.dto.response.AssociateResponse;
import com.db.polling.database.entity.AgendaEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AgendaMapper {

  AgendaEntity toEntity(AgendaDTO agendaDTO);

  AgendaDTO toDTO(AgendaEntity agendaEntity);

  AgendaResponse toDTOResponse(AgendaEntity agendaEntity);

}
