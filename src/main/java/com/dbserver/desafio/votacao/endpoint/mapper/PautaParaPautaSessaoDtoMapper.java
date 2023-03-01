package com.dbserver.desafio.votacao.endpoint.mapper;

import com.dbserver.desafio.votacao.usecase.domain.Pauta;
import com.dbserver.desafio.votacao.usecase.domain.Sessao;
import com.dbserver.desafio.votacao.endpoint.dto.PautaSessaoDTO;
import com.dbserver.desafio.votacao.endpoint.dto.SessaoDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import static org.mapstruct.factory.Mappers.getMapper;

@Mapper
public abstract class PautaParaPautaSessaoDtoMapper {

    public static final PautaParaPautaSessaoDtoMapper INSTANCE = getMapper(PautaParaPautaSessaoDtoMapper.class);

    @Mapping(source = "nome", target = "nomePauta")
    @Mapping(source = "descricao", target = "descricaoPauta")
    public abstract PautaSessaoDTO map(Pauta pauta);

    @Mapping(source = "duracao", target = "duracaoEmMunitos")
    public abstract SessaoDTO mapSessaoDTO(Sessao sessao);
}
