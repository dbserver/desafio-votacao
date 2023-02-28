package br.tec.db.desafio.business.service.implementation.validacao.sessao.votar;


import br.tec.db.desafio.business.service.implementation.validacao.sessao.AValidacaoVotarEmUmaSessao;
import br.tec.db.desafio.exception.BusinessException;

public class ValidarSessaoJaVotada extends AValidacaoVotarEmUmaSessao {


    public void validarSessao(Long id) {
        if(id != null){
            throw new BusinessException("Associado já votou");
        }
    }


}
