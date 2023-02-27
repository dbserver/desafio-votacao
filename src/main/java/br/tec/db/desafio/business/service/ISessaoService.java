package br.tec.db.desafio.business.service;

import br.tec.db.desafio.api.v1.dto.sessao.request.SessaoParaCriarRequestV1;
import br.tec.db.desafio.api.v1.dto.sessao.request.SessaoParaSaberTotalVotosRequestV1;
import br.tec.db.desafio.api.v1.dto.sessao.request.SessaoParaVotarRequestV1;
import br.tec.db.desafio.api.v1.dto.sessao.response.SessaoCriadaResponseV1;
import br.tec.db.desafio.api.v1.dto.sessao.response.SessaoTotalVotosResponseV1;
import br.tec.db.desafio.api.v1.dto.sessao.response.SessaoVotadaResponseV1;

public interface ISessaoService {
    SessaoCriadaResponseV1 criarUmaNovaSessao(SessaoParaCriarRequestV1 sessaoRequestV1);

    SessaoVotadaResponseV1 votarEmUmaSessao(SessaoParaVotarRequestV1 sessaoRequestV1);

    SessaoTotalVotosResponseV1 totalDeVotosDaSessao(SessaoParaSaberTotalVotosRequestV1 sessaoRequestV1);
}
