package br.tec.db.desafio.business.service.implementation.base;



import br.tec.db.desafio.api.v1.dto.associado.AssociadoRequestV1;
import br.tec.db.desafio.business.domain.Associado;
import br.tec.db.desafio.business.service.implementation.validacao.FactoryValidacao;
import br.tec.db.desafio.business.service.implementation.validacao.associado.AValidacaoCriarUmNovoAssociado;
import br.tec.db.desafio.repository.AssociadoRepository;

import java.util.List;

public class BaseAssociado {
    protected final AssociadoRepository associadoRepository;
    protected final List<AValidacaoCriarUmNovoAssociado> validacaoCriarUmNovoAssociadoList;


    public BaseAssociado(AssociadoRepository associadoRepository,
                            List<AValidacaoCriarUmNovoAssociado> validacaoCriarUmNovoAssociadoList
                            ) {
        this.associadoRepository = associadoRepository;
        this.validacaoCriarUmNovoAssociadoList = FactoryValidacao.createAValidacaoCriarUmNovoAssociado();

    }

    protected void valida(Object obj){
        if (obj instanceof AssociadoRequestV1)
            this.validacaoCriarUmNovoAssociadoList.forEach(v->v.validarAssociado((AssociadoRequestV1) obj));
        if (obj instanceof Associado)
            this.validacaoCriarUmNovoAssociadoList.forEach(v->v.validarAssociado((Associado) obj));

    }
}
