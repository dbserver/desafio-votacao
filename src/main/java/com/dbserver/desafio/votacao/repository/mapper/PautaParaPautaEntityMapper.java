package com.dbserver.desafio.votacao.repository.mapper;


import com.dbserver.desafio.votacao.usecase.domain.Pauta;
import com.dbserver.desafio.votacao.repository.entity.PautaEntity;
import org.mapstruct.Mapper;

import static org.mapstruct.factory.Mappers.getMapper;

@Mapper
public abstract class PautaParaPautaEntityMapper {

    public static final PautaParaPautaEntityMapper INSTANCE = getMapper(PautaParaPautaEntityMapper.class);

    public abstract PautaEntity map(Pauta pauta);
}
