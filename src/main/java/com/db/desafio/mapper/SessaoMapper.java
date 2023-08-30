package com.db.desafio.mapper;

import com.db.desafio.dto.SessaoVotacaoDto;
import com.db.desafio.entity.SessaoVotacao;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface SessaoMapper {

    SessaoMapper INSTANCE = Mappers.getMapper(SessaoMapper.class);

    SessaoVotacaoDto sessaoParaSessaoDto(SessaoVotacao sessaoVotacao);
    List<SessaoVotacaoDto> listaSessaoParaListaSessaoDto(List<SessaoVotacao> sessoes);
}
