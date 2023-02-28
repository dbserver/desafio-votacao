package br.tec.db.desafio.business.service.implementation.base;

import br.tec.db.desafio.business.domain.Sessao;
import br.tec.db.desafio.business.service.implementation.validacao.sessao.*;
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


    public BaseSessao(SessaoRepository sessaoRepository,
                      PautaRepository pautaRepository,
                      AssociadoRepository associadoRepository,
                      AssociadoSessaoRepository associadoSessaoRepository,
                      List<AValidacaoTotalDeVotosDaSessao> validacaoTotalDeVotosDaSessaoList,
                      List<AValidacaoCriarUmaNovaSessao> validacaoCriarUmaNovaSessaoList,
                      List<AValidacaoVotarEmUmaSessao> validacaoVotarEmUmaSessaoList
                      ) {
        this.sessaoRepository = sessaoRepository;
        this.pautaRepository = pautaRepository;
        this.associadoRepository = associadoRepository;
        this.associadoSessaoRepository = associadoSessaoRepository;
        this.validacaoTotalDeVotosDaSessaoList = FactoryBase.createAValidacaoTotalDeVotosDaSessao();
        this.validacaoCriarUmaNovaSessaoList = FactoryBase.createAValidacaoCriarUmaNovaSessao();
        this.validacaoVotarEmUmaSessaoList = FactoryBase.createAValidacaoVotarEmUmaSessao();
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
