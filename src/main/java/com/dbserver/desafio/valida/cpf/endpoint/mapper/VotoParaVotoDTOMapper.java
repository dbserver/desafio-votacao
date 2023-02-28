package com.dbserver.desafio.valida.cpf.endpoint.mapper;

import com.dbserver.desafio.valida.cpf.endpoint.dto.VotoDTO;
import com.dbserver.desafio.valida.cpf.usecase.domain.Voto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import static org.mapstruct.factory.Mappers.getMapper;

@Mapper
public abstract class VotoParaVotoDTOMapper {

    public static final VotoParaVotoDTOMapper INSTANCE = getMapper(VotoParaVotoDTOMapper.class);

    @Mapping(source = "pauta.idPauta", target = "idPauta")
    public abstract VotoDTO map(Voto voto);
}
