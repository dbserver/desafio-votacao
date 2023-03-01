package br.tec.db.desafio.business.service.implementation.validacao.sessao.criarsessao;

import br.tec.db.desafio.exception.BusinessException;


public class ValidarInexistentePorId {



    public void validar(Long id) {
        if(id == null){
            throw new BusinessException("pauta inexistente");
        }
    }
}
