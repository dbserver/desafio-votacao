package br.tec.db.desafio.business.service.implementation.validacao.associado;


import br.tec.db.desafio.business.domain.Associado;
import br.tec.db.desafio.exception.BusinessException;


public class ValidarNaoPodeSerNulo {




    public void validar(Associado dado) {
            if(dado!=null){
                throw new BusinessException("Associado já existente");
            }
    }


}
