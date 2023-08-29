package com.db.desafio.mapper;

import com.db.desafio.dto.SessaoDto;
import com.db.desafio.entity.Sessao;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface SessaoMapper {

    SessaoMapper INSTANCE = Mappers.getMapper(SessaoMapper.class);

    SessaoDto sessaoParaSessaoDto(Sessao sessao);
    List<SessaoDto> listaSessaoParaListaSessaoDto(List<Sessao> sessoes);
}
