package db.desafiovotacao.mappers;

import db.desafiovotacao.dto.PautaAtualizarRequest;
import db.desafiovotacao.dto.PautaRequest;
import db.desafiovotacao.model.Pauta;

public class PautaMapper {

    public static Pauta mapearPauta(PautaRequest pautaRequest){

        Pauta pauta = new Pauta();

        pauta.setTitulo(pautaRequest.titulo());
        pauta.setDescricao(pautaRequest.descricao());
        pauta.setSessao(SessaoMapper.mapearSessao(pautaRequest.sessaoRequest()));

        return pauta;
    }

    public static Pauta mapearPauta(PautaAtualizarRequest pautaAtualizarRequest){

        Pauta pauta = new Pauta();

        pauta.setId(pautaAtualizarRequest.id());
        pauta.setTitulo(pautaAtualizarRequest.titulo());
        pauta.setDescricao(pautaAtualizarRequest.descricao());
        pauta.setSessao(SessaoMapper.mapearSessao(pautaAtualizarRequest.sessaoRequest()));

        return pauta;
    }
}
