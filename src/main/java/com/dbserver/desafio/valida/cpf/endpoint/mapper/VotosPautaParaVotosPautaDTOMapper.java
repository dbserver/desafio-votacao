package com.dbserver.desafio.valida.cpf.endpoint.mapper;

import com.dbserver.desafio.valida.cpf.usecase.domain.VotosPauta;
import com.dbserver.desafio.valida.cpf.endpoint.dto.VotosPautaDTO;
import org.mapstruct.Mapper;

import static org.mapstruct.factory.Mappers.getMapper;

@Mapper
public abstract class VotosPautaParaVotosPautaDTOMapper {

    public static final VotosPautaParaVotosPautaDTOMapper INSTANCE = getMapper(VotosPautaParaVotosPautaDTOMapper.class);

    public abstract VotosPautaDTO map(VotosPauta votosPauta);
}
