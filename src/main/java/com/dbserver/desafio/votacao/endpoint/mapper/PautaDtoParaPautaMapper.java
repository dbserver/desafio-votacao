package com.dbserver.desafio.votacao.endpoint.mapper;

import com.dbserver.desafio.votacao.endpoint.dto.PautaDTO;
import com.dbserver.desafio.votacao.usecase.domain.Pauta;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import static org.mapstruct.factory.Mappers.getMapper;

@Mapper
public abstract class PautaDtoParaPautaMapper {

    public static final PautaDtoParaPautaMapper INSTANCE = getMapper(PautaDtoParaPautaMapper.class);

    @Mapping(source = "nomePauta", target = "nome")
    @Mapping(source = "descricaoPauta", target = "descricao")
    public abstract Pauta map(PautaDTO pautaDTO);
}
