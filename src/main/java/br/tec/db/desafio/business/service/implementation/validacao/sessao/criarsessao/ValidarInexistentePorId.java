package br.tec.db.desafio.business.service.implementation.validacao.sessao.criarsessao;

import br.tec.db.desafio.exception.BusinessException;
import br.tec.db.desafio.exception.NotFoundException;


public class ValidarInexistentePorId {



    public void validar(Long id) {
        if(id == null){
            throw new NotFoundException("pauta inexistente");
        }
    }
}
