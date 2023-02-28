package br.tec.db.desafio.business.service.implementation.validacao.sessao;

import br.tec.db.desafio.business.domain.Sessao;
import br.tec.db.desafio.business.service.implementation.validacao.sessao.criarsessao.ValidarCriarSessaoComPoucoCaracter;
import br.tec.db.desafio.business.service.implementation.validacao.sessao.criarsessao.ValidarCriarSessaoInexistente;
import br.tec.db.desafio.business.service.implementation.validacao.sessao.criarsessao.ValidarSessaoRepetida;
import br.tec.db.desafio.business.service.implementation.validacao.sessao.criarsessao.ValidarSessaoVazia;
import lombok.NoArgsConstructor;


public abstract class AValidacaoCriarUmaNovaSessao {
    public void validarSessao(Sessao sessao) {

    }

    public void validarSessao(Long id) {

    }


}
