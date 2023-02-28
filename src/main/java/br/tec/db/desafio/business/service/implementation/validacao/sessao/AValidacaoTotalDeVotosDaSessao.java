package br.tec.db.desafio.business.service.implementation.validacao.sessao;

import br.tec.db.desafio.business.domain.Sessao;
import br.tec.db.desafio.business.service.implementation.validacao.sessao.totalvotos.ValidarTotalSessaoComPoucoCaracter;
import br.tec.db.desafio.business.service.implementation.validacao.sessao.totalvotos.ValidarTotalSessaoInexistente;
import lombok.NoArgsConstructor;


public abstract class AValidacaoTotalDeVotosDaSessao {
    public void validarSessao(Sessao sessao, Long id) {

    }

    public void validarSessao(Long id) {

    }
}
