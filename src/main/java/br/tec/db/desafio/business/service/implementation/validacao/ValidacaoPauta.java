package br.tec.db.desafio.business.service.implementation.validacao;

import br.tec.db.desafio.api.v1.dto.PautaRequestV1;
import br.tec.db.desafio.business.entity.Pauta;

public interface ValidacaoPauta {
    void validarPauta(PautaRequestV1 pautaRequestV1);
}
