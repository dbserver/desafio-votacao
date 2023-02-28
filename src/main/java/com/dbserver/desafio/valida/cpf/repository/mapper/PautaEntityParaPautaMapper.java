package com.dbserver.desafio.valida.cpf.repository.mapper;


import com.dbserver.desafio.valida.cpf.usecase.domain.Pauta;
import com.dbserver.desafio.valida.cpf.repository.entity.PautaEntity;
import org.mapstruct.Mapper;

import static org.mapstruct.factory.Mappers.getMapper;

@Mapper
public abstract class PautaEntityParaPautaMapper {

    public static final PautaEntityParaPautaMapper INSTANCE = getMapper(PautaEntityParaPautaMapper.class);

    public abstract Pauta map(PautaEntity pautaEntity);
}
