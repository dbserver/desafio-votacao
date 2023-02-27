package com.dbserver.desafio.votacao.endpoint.mapper;

import com.dbserver.desafio.votacao.endpoint.dto.PautaDTO;
import com.dbserver.desafio.votacao.usecase.domain.Pauta;
import org.mapstruct.Mapper;

import static org.mapstruct.factory.Mappers.getMapper;

@Mapper
public abstract class PautaDtoParaPautaMapper {

    public static final PautaDtoParaPautaMapper INSTANCE = getMapper(PautaDtoParaPautaMapper.class);

    public abstract Pauta map(PautaDTO pautaDTO);
}
