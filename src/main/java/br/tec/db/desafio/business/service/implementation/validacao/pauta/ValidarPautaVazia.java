package br.tec.db.desafio.business.service.implementation.validacao.pauta;

import br.tec.db.desafio.exception.BusinessException;


public class ValidarPautaVazia {



    public void validar(String dado) {

            if(dado.isEmpty()){
                throw new BusinessException("Pauta vazia");
            }

    }
}
