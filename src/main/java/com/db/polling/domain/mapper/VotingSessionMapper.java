package com.db.polling.domain.mapper;

import com.db.polling.api.dto.VotingSessionDTO;
import com.db.polling.api.dto.response.VotingSessionResponse;
import com.db.polling.database.entity.VotingSessionEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface VotingSessionMapper {

  VotingSessionEntity toEntity(VotingSessionDTO votingSessionDTO);

  @Mapping(target = "agendaId", source = "agendaEntity.agendaId")
  VotingSessionDTO toDTO(VotingSessionEntity votingSessionEntity);

}
