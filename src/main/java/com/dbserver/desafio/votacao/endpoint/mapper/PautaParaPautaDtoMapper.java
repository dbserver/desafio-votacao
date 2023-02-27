package com.dbserver.desafio.votacao.endpoint.mapper;

import com.dbserver.desafio.votacao.endpoint.dto.PautaDTO;
import com.dbserver.desafio.votacao.usecase.domain.Pauta;
import org.mapstruct.Mapper;

import static org.mapstruct.factory.Mappers.getMapper;

@Mapper
public abstract class PautaParaPautaDtoMapper {

    public static final PautaParaPautaDtoMapper INSTANCE = getMapper(PautaParaPautaDtoMapper.class);

    public abstract PautaDTO map(Pauta pauta);
}
