package br.tec.db.desafio.business.service.implementation.validacao.sessao.votar;


import br.tec.db.desafio.business.service.implementation.validacao.sessao.AValidacaoVotarEmUmaSessao;
import br.tec.db.desafio.exception.BusinessException;

import java.time.LocalDateTime;

public class ValidarSessaoExpirada extends AValidacaoVotarEmUmaSessao {


    public void validarSessao(LocalDateTime duracao) {
        if(duracao.isBefore(LocalDateTime.now())){
            throw new BusinessException("Sessão expirada");
        }
    }
}
