package br.tec.db.desafio.business.service;

import br.tec.db.desafio.api.v1.dto.PautaRequestV1;
import br.tec.db.desafio.api.v1.dto.PautaResponseV1;

public interface PautaService {
    PautaResponseV1 criarUmaNovaPauta(PautaRequestV1 pautaRequestV1);
}
