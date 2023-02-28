package com.dbserver.desafio.valida.cpf.endpoint.mapper;

import com.dbserver.desafio.valida.cpf.usecase.domain.Pauta;
import com.dbserver.desafio.valida.cpf.endpoint.dto.PautaDTO;
import org.mapstruct.Mapper;

import static org.mapstruct.factory.Mappers.getMapper;

@Mapper
public abstract class PautaDtoParaPautaMapper {

    public static final PautaDtoParaPautaMapper INSTANCE = getMapper(PautaDtoParaPautaMapper.class);

    public abstract Pauta map(PautaDTO pautaDTO);
}
