package br.tec.db.desafio.business.service.implementation.validacao;

import br.tec.db.desafio.api.v1.dto.PautaRequestV1;
import br.tec.db.desafio.exception.BusinessException;

public class ValidarPautaComPoucoCaracter implements ValidacaoPauta{
    @Override
    public void validarPauta(PautaRequestV1 pautaRequestV1) {
        if(pautaRequestV1.getAssunto().length() < 3){
            throw new BusinessException("A pauta não pode estar vazia");
        }
    }
}
