package br.tec.db.desafio.business.service.implementation.validacao.sessao;

import br.tec.db.desafio.api.v1.dto.pauta.PautaRequestV1;
import br.tec.db.desafio.api.v1.dto.sessao.SessaoRequestV1;

public interface ValidacaoSessao {
    void validarSessao(SessaoRequestV1 sessaoRequestV1);
}
