package br.tec.db.desafio.business.service.implementation.validacao.sessao.totalvotos;

import br.tec.db.desafio.business.service.implementation.validacao.sessao.AValidacaoTotalDeVotosDaSessao;
import br.tec.db.desafio.exception.BusinessException;
import lombok.NoArgsConstructor;


public class ValidarTotalSessaoInexistente extends AValidacaoTotalDeVotosDaSessao {



    public void validarSessao(Long id) {
        if(id == null){
            throw new BusinessException("Sessão inexistente");
        }
    }

}
