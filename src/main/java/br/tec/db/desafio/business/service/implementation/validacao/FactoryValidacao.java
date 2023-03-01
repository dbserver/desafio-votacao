package br.tec.db.desafio.business.service.implementation.validacao;

import br.tec.db.desafio.business.service.implementation.validacao.associado.*;
import br.tec.db.desafio.business.service.implementation.validacao.pauta.AValidacaoCriarUmaNovaPauta;
import br.tec.db.desafio.business.service.implementation.validacao.pauta.ValidarPautaComPoucoCaracter;
import br.tec.db.desafio.business.service.implementation.validacao.pauta.ValidarPautaJaExistente;
import br.tec.db.desafio.business.service.implementation.validacao.pauta.ValidarPautaVazia;
import br.tec.db.desafio.business.service.implementation.validacao.sessao.AValidacaoCriarUmaNovaSessao;
import br.tec.db.desafio.business.service.implementation.validacao.sessao.AValidacaoTotalDeVotosDaSessao;
import br.tec.db.desafio.business.service.implementation.validacao.sessao.AValidacaoVotarEmUmaSessao;
import br.tec.db.desafio.business.service.implementation.validacao.sessao.criarsessao.ValidarCriarSessaoComPoucoCaracter;
import br.tec.db.desafio.business.service.implementation.validacao.sessao.criarsessao.ValidarCriarSessaoInexistente;
import br.tec.db.desafio.business.service.implementation.validacao.sessao.criarsessao.ValidarSessaoRepetida;
import br.tec.db.desafio.business.service.implementation.validacao.sessao.criarsessao.ValidarSessaoVazia;
import br.tec.db.desafio.business.service.implementation.validacao.sessao.totalvotos.ValidarTotalSessaoComPoucoCaracter;
import br.tec.db.desafio.business.service.implementation.validacao.sessao.totalvotos.ValidarTotalSessaoInexistente;
import br.tec.db.desafio.business.service.implementation.validacao.sessao.votar.ValidarSessaoExpirada;
import br.tec.db.desafio.business.service.implementation.validacao.sessao.votar.ValidarSessaoJaVotada;
import br.tec.db.desafio.business.service.implementation.validacao.sessao.votar.ValidarVotarSessaoComPoucoCaracter;
import lombok.NoArgsConstructor;

import java.util.Arrays;
import java.util.List;

@NoArgsConstructor
public class FactoryValidacao {

    public static List<AValidacaoTotalDeVotosDaSessao> createAValidacaoTotalDeVotosDaSessao(){
        return Arrays.asList(new ValidarTotalSessaoInexistente(), new ValidarTotalSessaoComPoucoCaracter());
    }
    public static List<AValidacaoCriarUmaNovaSessao> createAValidacaoCriarUmaNovaSessao(){
        return Arrays.asList(new ValidarCriarSessaoComPoucoCaracter(), new ValidarCriarSessaoInexistente(),new ValidarSessaoRepetida(),new ValidarSessaoVazia());
    }
    public static List<AValidacaoVotarEmUmaSessao> createAValidacaoVotarEmUmaSessao(){
        return Arrays.asList(new ValidarSessaoExpirada(), new ValidarSessaoJaVotada(), new ValidarVotarSessaoComPoucoCaracter());
    }

    public static List<AValidacaoCriarUmaNovaPauta> createAValidacaoCriarUmaNovaPauta(){
        return Arrays.asList(new ValidarPautaVazia(), new ValidarPautaComPoucoCaracter(), new ValidarPautaJaExistente());
    }

    public static List<AValidacaoCriarUmNovoAssociado> createAValidacaoCriarUmNovoAssociado(){
        return Arrays.asList(new ValidarAssociadoCpf(), new ValidarAssociadoJaExistente());
    }

    public static List<AValidacaoFakeClientAssociado> createAValidacaoFakeClientAssociado(){
        return Arrays.asList(new ValidarFakeAssociadoCpf());
    }
}
