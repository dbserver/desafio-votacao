package br.tec.db.desafio.business.service.implementation.base;



import br.tec.db.desafio.business.service.implementation.validacao.FactoryValidacao;
import br.tec.db.desafio.repository.PautaRepository;

import java.util.List;

public class BasePauta {
    protected final PautaRepository pautaRepository;

    public BasePauta(PautaRepository pautaRepository) {
        this.pautaRepository = pautaRepository;
    }
    protected static final FactoryValidacao valida = new FactoryValidacao();

}
