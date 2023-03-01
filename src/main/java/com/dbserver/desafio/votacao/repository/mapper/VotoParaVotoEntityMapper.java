package com.dbserver.desafio.votacao.repository.mapper;


import com.dbserver.desafio.votacao.usecase.domain.Voto;
import com.dbserver.desafio.votacao.repository.entity.VotoEntity;
import org.mapstruct.Mapper;

import static org.mapstruct.factory.Mappers.getMapper;

@Mapper
public abstract class VotoParaVotoEntityMapper {

    public static final VotoParaVotoEntityMapper INSTANCE = getMapper(VotoParaVotoEntityMapper.class);

    public abstract VotoEntity map(Voto voto);
}
