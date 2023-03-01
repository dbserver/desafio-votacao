package br.tec.db.desafio.business.service.implementation.validacao.associado;


import br.tec.db.desafio.api.v1.dto.associado.AssociadoRequestV1;
import br.tec.db.desafio.api.v1.dto.associado.client.AssociadoClientRequestV1;
import br.tec.db.desafio.exception.BusinessException;


public class ValidarFakeAssociadoCpf extends AValidacaoFakeClientAssociado {




    @Override
    public void validarAssociado(AssociadoClientRequestV1 associadoClientRequestV1) {
        if(associadoClientRequestV1.getCpf().length() < 11){
            throw new BusinessException("CPF com menos de 11 dígitos");
        }
        if(associadoClientRequestV1.getCpf().length() > 11){
            throw new BusinessException("CPF com mais de 11 dígitos");
        }
    }

}
