package com.dbserver.desafio.valida.cpf.endpoint.mapper;

import com.dbserver.desafio.valida.cpf.usecase.domain.Pauta;
import com.dbserver.desafio.valida.cpf.endpoint.dto.PautaDTO;
import org.mapstruct.Mapper;

import static org.mapstruct.factory.Mappers.getMapper;

@Mapper
public abstract class PautaParaPautaDtoMapper {

    public static final PautaParaPautaDtoMapper INSTANCE = getMapper(PautaParaPautaDtoMapper.class);

    public abstract PautaDTO map(Pauta pauta);
}
