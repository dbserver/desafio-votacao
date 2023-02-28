package com.dbserver.desafio.valida.cpf.repository.mapper;

import com.dbserver.desafio.valida.cpf.usecase.domain.Voto;
import com.dbserver.desafio.valida.cpf.repository.entity.VotoEntity;
import org.mapstruct.Mapper;

import java.util.List;

import static org.mapstruct.factory.Mappers.getMapper;

@Mapper
public abstract class VotoEntityParaVotoMapper {

    public static final VotoEntityParaVotoMapper INSTANCE = getMapper(VotoEntityParaVotoMapper.class);

    public abstract Voto map(VotoEntity votoEntity);

    public abstract List<Voto> mapList(List<VotoEntity> votoEntityList);
}
