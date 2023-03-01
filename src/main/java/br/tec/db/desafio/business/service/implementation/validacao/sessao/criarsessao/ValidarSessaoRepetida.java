package br.tec.db.desafio.business.service.implementation.validacao.sessao.criarsessao;

import br.tec.db.desafio.business.domain.Sessao;
import br.tec.db.desafio.exception.BusinessException;
import lombok.NoArgsConstructor;


public class ValidarSessaoRepetida {


    public void validar(Sessao dado) {

        if(dado!=null){
            throw new BusinessException("Já existe uma sessao com este tema");
        }

    }



}
