package br.tec.db.desafio.business.service;

import br.tec.db.desafio.api.v1.dto.pauta.PautaRequestV1;
import br.tec.db.desafio.api.v1.dto.pauta.PautaResponseV1;

public interface IPautaService {
    PautaResponseV1 criarUmaNovaPauta(PautaRequestV1 pautaRequestV1);
}
