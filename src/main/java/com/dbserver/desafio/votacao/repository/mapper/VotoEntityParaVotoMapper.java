package com.dbserver.desafio.votacao.repository.mapper;

import com.dbserver.desafio.votacao.repository.entity.VotoEntity;
import com.dbserver.desafio.votacao.usecase.domain.Voto;
import org.mapstruct.Mapper;

import java.util.List;

import static org.mapstruct.factory.Mappers.getMapper;

@Mapper
public abstract class VotoEntityParaVotoMapper {

    public static final VotoEntityParaVotoMapper INSTANCE = getMapper(VotoEntityParaVotoMapper.class);

    public abstract Voto map(VotoEntity votoEntity);

    public abstract List<Voto> mapList(List<VotoEntity> votoEntityList);
}
