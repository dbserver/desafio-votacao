package br.tec.db.desafio.business.service;

import br.tec.db.desafio.api.v1.dto.pauta.PautaRequestV1;
import br.tec.db.desafio.api.v1.dto.pauta.PautaResponseV1;
import br.tec.db.desafio.api.v1.dto.sessao.SessaoRequestV1;
import br.tec.db.desafio.api.v1.dto.sessao.SessaoResponseV1;

public interface SessaoService {
    SessaoResponseV1 criarUmaNovaSessao(SessaoRequestV1 sessaoRequestV1);
}
