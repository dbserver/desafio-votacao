package com.dbserver.desafio.votacao.repository.mapper;


import com.dbserver.desafio.votacao.repository.entity.PautaEntity;
import com.dbserver.desafio.votacao.usecase.domain.Pauta;
import org.mapstruct.Mapper;

import static org.mapstruct.factory.Mappers.getMapper;

@Mapper
public abstract class PautaEntityParaPautaMapper {

    public static final PautaEntityParaPautaMapper INSTANCE = getMapper(PautaEntityParaPautaMapper.class);

    public abstract Pauta map(PautaEntity pautaEntity);
}
