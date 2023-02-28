package br.tec.db.desafio.business.service.implementation.validacao.sessao.criarsessao;

import br.tec.db.desafio.business.domain.Sessao;
import br.tec.db.desafio.business.service.implementation.validacao.sessao.AValidacaoCriarUmaNovaSessao;
import br.tec.db.desafio.exception.BusinessException;

public class ValidarSessaoRepetida extends AValidacaoCriarUmaNovaSessao {

    public void validarSessao(Sessao sessao) {

        if(sessao!=null){
            throw new BusinessException("Já existe uma sessao com este tema");
        }

    }



}
