package br.tec.db.desafio.business.service.implementation.validacao.sessao.criarsessao;

import br.tec.db.desafio.business.service.implementation.validacao.sessao.AValidacaoCriarUmaNovaSessao;
import br.tec.db.desafio.exception.BusinessException;

public class ValidarCriarSessaoInexistente extends AValidacaoCriarUmaNovaSessao {



    public void validarSessao(Long id) {
        if(id == null){
            throw new BusinessException("id inexistente");
        }
    }
}
