package br.tec.db.desafio.business.service.implementation.validacao.sessao;

import br.tec.db.desafio.api.v1.dto.sessao.request.SessaoParaCriarRequestV1;

public interface ValidacaoSessao {
    void validarSessao(SessaoParaCriarRequestV1 sessaoRequestV1);
}
