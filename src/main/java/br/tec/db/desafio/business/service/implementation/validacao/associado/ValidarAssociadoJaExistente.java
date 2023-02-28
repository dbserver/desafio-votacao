package br.tec.db.desafio.business.service.implementation.validacao.associado;


import br.tec.db.desafio.business.domain.Associado;
import br.tec.db.desafio.exception.BusinessException;


public class ValidarAssociadoJaExistente extends AValidacaoCriarUmNovoAssociado {



    @Override
    public void validarAssociado(Associado associado) {
        if(associado!=null){
            throw new BusinessException("Associado já existente");
        }
    }


}
