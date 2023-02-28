package br.tec.db.desafio.business.service.implementation.validacao.associado;


import br.tec.db.desafio.api.v1.dto.associado.AssociadoRequestV1;
import br.tec.db.desafio.exception.BusinessException;

public class ValidarAssociadoCpf extends AValidacaoCriarUmNovoAssociado {



    @Override
    public void validarAssociado(AssociadoRequestV1 associadoRequestV1) {
        if(associadoRequestV1.getCpf().length() < 11){
            throw new BusinessException("CPF com menos de 11 dígitos");
        }
        if(associadoRequestV1.getCpf().length() > 11){
            throw new BusinessException("CPF com mais de 11 dígitos");
        }
    }

}
