package br.tec.db.desafio.business.service.implementation.validacao.sessao;

import br.tec.db.desafio.business.domain.Sessao;
import br.tec.db.desafio.business.service.implementation.validacao.sessao.votar.ValidarSessaoExpirada;
import br.tec.db.desafio.business.service.implementation.validacao.sessao.votar.ValidarSessaoJaVotada;
import br.tec.db.desafio.business.service.implementation.validacao.sessao.votar.ValidarVotarSessaoComPoucoCaracter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

public abstract class AValidacaoVotarEmUmaSessao {
    public void validarSessao(Sessao sessao) {

    }

    public void validarSessao(Long id) {

    }

    public void validarSessao(LocalDateTime duracao) {

    }
}
