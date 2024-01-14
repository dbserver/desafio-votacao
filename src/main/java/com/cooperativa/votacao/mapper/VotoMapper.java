package com.cooperativa.votacao.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.cooperativa.votacao.dto.VotoDTO;
import com.cooperativa.votacao.entity.VotoEntity;

@Mapper(componentModel = "spring")
public interface VotoMapper {

	@Mapping(source = "idPauta", target = "pauta.id")
	VotoEntity DTOtoEntity(VotoDTO votoDTO);
}
