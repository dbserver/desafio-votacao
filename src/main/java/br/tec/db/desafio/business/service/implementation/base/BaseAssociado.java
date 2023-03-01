package br.tec.db.desafio.business.service.implementation.base;



import br.tec.db.desafio.business.service.implementation.validacao.FactoryValidacao;
import br.tec.db.desafio.repository.AssociadoRepository;

import java.util.List;

public class BaseAssociado {
    protected final AssociadoRepository associadoRepository;

    public BaseAssociado(AssociadoRepository associadoRepository) {
        this.associadoRepository = associadoRepository;
    }

    protected static final FactoryValidacao valida = new FactoryValidacao();

}
