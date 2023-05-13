package com.db.polling.domain.mapper;


import com.db.polling.api.dto.VoteDTO;
import com.db.polling.api.dto.response.VoteResponse;
import com.db.polling.database.entity.VoteEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface VoteMapper {

  VoteEntity toEntity(VoteDTO voteDTO);

  VoteResponse toDTO(VoteEntity voteEntity);

}
