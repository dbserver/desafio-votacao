package com.dbserver.desafio.valida.cpf.endpoint.mapper;

import com.dbserver.desafio.valida.cpf.endpoint.dto.VotoDTO;
import com.dbserver.desafio.valida.cpf.usecase.domain.Voto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import static org.mapstruct.factory.Mappers.getMapper;

@Mapper
public abstract class VotoDtoParaVotoMapper {

    public static final VotoDtoParaVotoMapper INSTANCE = getMapper(VotoDtoParaVotoMapper.class);

    @Mapping(source = "idPauta", target = "pauta.idPauta")
    public abstract Voto map(VotoDTO votoDTO);
}
