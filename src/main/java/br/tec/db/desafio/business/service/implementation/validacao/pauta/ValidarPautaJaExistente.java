package br.tec.db.desafio.business.service.implementation.validacao.pauta;

import br.tec.db.desafio.business.domain.Pauta;
import br.tec.db.desafio.exception.BusinessException;


public class ValidarPautaJaExistente {



    public void validar(Pauta dado) {

            if(dado!=null){
                throw new BusinessException("Já existe uma pauta com este tema");
            }

    }
}
