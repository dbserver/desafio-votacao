package br.tec.db.desafio.business.service.implementation.validacao.pauta;

import br.tec.db.desafio.api.v1.dto.pauta.PautaRequestV1;
import br.tec.db.desafio.exception.BusinessException;

public class ValidarPautaComPoucoCaracter implements ValidacaoPauta{
    @Override
    public void validarPauta(PautaRequestV1 pautaRequestV1) {
        if(pautaRequestV1.getAssunto().length() < 3){
            throw new BusinessException("Pauta com menos de 3 caracteres");
        }
    }
}
