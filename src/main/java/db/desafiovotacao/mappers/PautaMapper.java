package db.desafiovotacao.mappers;

import db.desafiovotacao.dto.PautaAtualizarRequest;
import db.desafiovotacao.dto.PautaRequest;
import db.desafiovotacao.model.Pauta;

public class PautaMapper {

    public static Pauta mapearPauta(PautaRequest pautaRequest){

        return Pauta.builder()
                .titulo(pautaRequest.titulo())
                .descricao(pautaRequest.descricao())
                .sessao(SessaoMapper.mapearSessao(pautaRequest.sessaoRequest()))
                .build();
    }

    public static Pauta mapearPauta(PautaAtualizarRequest pautaAtualizarRequest){

        return Pauta.builder()
                .id(pautaAtualizarRequest.id())
                .titulo(pautaAtualizarRequest.titulo())
                .descricao(pautaAtualizarRequest.descricao())
                .sessao(SessaoMapper.mapearSessao(pautaAtualizarRequest.sessaoRequest()))
                .build();
    }
}
