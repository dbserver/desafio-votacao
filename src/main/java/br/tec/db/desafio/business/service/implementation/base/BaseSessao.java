package br.tec.db.desafio.business.service.implementation.base;

import br.tec.db.desafio.business.service.implementation.validacao.FactoryValidacao;
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


    public BaseSessao(SessaoRepository sessaoRepository,
                      PautaRepository pautaRepository,
                      AssociadoRepository associadoRepository,
                      AssociadoSessaoRepository associadoSessaoRepository
                      ) {
        this.sessaoRepository = sessaoRepository;
        this.pautaRepository = pautaRepository;
        this.associadoRepository = associadoRepository;
        this.associadoSessaoRepository = associadoSessaoRepository;
    }




    protected static final FactoryValidacao valida = new FactoryValidacao();


}
