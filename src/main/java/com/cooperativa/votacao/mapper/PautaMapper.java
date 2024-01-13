package com.cooperativa.votacao.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.cooperativa.votacao.dto.PautaDTO;
import com.cooperativa.votacao.entity.PautaEntity;


@Mapper(componentModel = "spring")
public interface PautaMapper {
	        
	@Mapping(source = "statusSessao.nomeStatusSessao", target = "statusSessao")
	PautaDTO entityToDTO(PautaEntity pautaEntity);

}
