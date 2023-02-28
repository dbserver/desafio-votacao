package br.tec.db.desafio.business.service.implementation.base;

import br.tec.db.desafio.business.domain.Sessao;
import br.tec.db.desafio.business.service.implementation.validacao.sessao.*;
import br.tec.db.desafio.business.service.implementation.validacao.sessao.criarsessao.ValidarCriarSessaoComPoucoCaracter;
import br.tec.db.desafio.business.service.implementation.validacao.sessao.criarsessao.ValidarCriarSessaoInexistente;
import br.tec.db.desafio.business.service.implementation.validacao.sessao.criarsessao.ValidarSessaoRepetida;
import br.tec.db.desafio.business.service.implementation.validacao.sessao.criarsessao.ValidarSessaoVazia;
import br.tec.db.desafio.business.service.implementation.validacao.sessao.totalvotos.ValidarTotalSessaoComPoucoCaracter;
import br.tec.db.desafio.business.service.implementation.validacao.sessao.totalvotos.ValidarTotalSessaoInexistente;
import br.tec.db.desafio.business.service.implementation.validacao.sessao.votar.ValidarSessaoExpirada;
import br.tec.db.desafio.business.service.implementation.validacao.sessao.votar.ValidarSessaoJaVotada;
import br.tec.db.desafio.business.service.implementation.validacao.sessao.votar.ValidarVotarSessaoComPoucoCaracter;
import br.tec.db.desafio.repository.AssociadoRepository;
import br.tec.db.desafio.repository.AssociadoSessaoRepository;
import br.tec.db.desafio.repository.PautaRepository;
import br.tec.db.desafio.repository.SessaoRepository;

import java.util.List;

public class BaseSessao {
    protected final SessaoRepository sessaoRepository;
    protected final PautaRepository pautaRepository;
    protected final AssociadoRepository associadoRepository;
    protected final AssociadoSessaoRepository associadoSessaoRepository;
    private final List<AValidacaoTotalDeVotosDaSessao> validacaoTotalDeVotosDaSessaoList;
    private final List<AValidacaoCriarUmaNovaSessao> validacaoCriarUmaNovaSessaoList;
    private final List<AValidacaoVotarEmUmaSessao> validacaoVotarEmUmaSessaoList;
    private final ValidarSessaoExpirada validarSessaoExpirada = new ValidarSessaoExpirada();
    private final ValidarSessaoJaVotada validarSessaoJaVotada = new ValidarSessaoJaVotada();
    private final ValidarVotarSessaoComPoucoCaracter validarVotarSessaoComPoucoCaracter = new ValidarVotarSessaoComPoucoCaracter();
    private final ValidarCriarSessaoComPoucoCaracter validarCriarSessaoComPoucoCaracter = new ValidarCriarSessaoComPoucoCaracter();
    private final ValidarCriarSessaoInexistente validarCriarSessaoInexistente = new ValidarCriarSessaoInexistente();
    private final ValidarSessaoRepetida validarSessaoRepetida = new ValidarSessaoRepetida();
    private final ValidarSessaoVazia validarSessaoVazia = new ValidarSessaoVazia();
    private final ValidarTotalSessaoInexistente validarTotalSessaoInexistente = new ValidarTotalSessaoInexistente();
    private final ValidarTotalSessaoComPoucoCaracter validarTotalSessaoComPoucoCaracter = new ValidarTotalSessaoComPoucoCaracter();


    public BaseSessao(SessaoRepository sessaoRepository,
                      PautaRepository pautaRepository,
                      AssociadoRepository associadoRepository,
                      AssociadoSessaoRepository associadoSessaoRepository
                      ) {
        this.sessaoRepository = sessaoRepository;
        this.pautaRepository = pautaRepository;
        this.associadoRepository = associadoRepository;
        this.associadoSessaoRepository = associadoSessaoRepository;
        this.validacaoTotalDeVotosDaSessaoList =
                List.of(validarTotalSessaoInexistente, validarTotalSessaoComPoucoCaracter);

        this.validacaoCriarUmaNovaSessaoList =
                List.of(validarCriarSessaoInexistente, validarCriarSessaoComPoucoCaracter, validarSessaoRepetida, validarSessaoVazia);

         this.validacaoVotarEmUmaSessaoList =
                List.of(validarSessaoExpirada, validarSessaoJaVotada, validarVotarSessaoComPoucoCaracter);
    }

    protected void validaCriar(Object obj){
        if (obj instanceof Long) {
            this.validacaoCriarUmaNovaSessaoList.forEach(v -> v.validarSessao((Long) obj));
        }
        if (obj instanceof Sessao) {
            this.validacaoCriarUmaNovaSessaoList.forEach(v -> v.validarSessao((Sessao) obj));
        }
    }

    protected void validaVotar(Object obj){
        if (obj instanceof Long) {
            this.validacaoVotarEmUmaSessaoList.forEach(v -> v.validarSessao((Long) obj));
        }
        if (obj instanceof Sessao) {
            this.validacaoVotarEmUmaSessaoList.forEach(v -> v.validarSessao((Sessao) obj));
        }
    }

    protected void validaTotal(Object obj){
        if (obj instanceof Long) {
            this.validacaoTotalDeVotosDaSessaoList.forEach(v -> v.validarSessao((Long) obj));
        }
    }
}
