package br.tec.db.desafio.business.service.implementation.validacao;

import br.tec.db.desafio.api.v1.dto.PautaRequestV1;
import br.tec.db.desafio.business.entity.Pauta;
import br.tec.db.desafio.exception.BusinessException;

public class ValidarPautaVazia implements ValidacaoPauta{
    @Override
    public void validarPauta(PautaRequestV1 pautaRequestV1) {
        if(pautaRequestV1.getAssunto().isEmpty()){
            throw new BusinessException("A pauta não pode estar vazia");
        }
    }
}
