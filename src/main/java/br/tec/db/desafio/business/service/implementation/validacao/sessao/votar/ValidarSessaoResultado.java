package br.tec.db.desafio.business.service.implementation.validacao.sessao.votar;


import br.tec.db.desafio.business.domain.Sessao;
import br.tec.db.desafio.exception.BusinessException;

import java.time.LocalDateTime;

public class ValidarSessaoResultado {


    public void validar(Sessao sessao) {
        sessao.setSessaoEncerrada(sessao.getDuracao().isBefore(LocalDateTime.now()));
    }
}
