package br.tec.db.desafio.business.service.implementation.validacao.pauta;

import br.tec.db.desafio.api.v1.dto.pauta.PautaRequestV1;

public interface ValidacaoPauta {
    void validarPauta(PautaRequestV1 pautaRequestV1);
}
