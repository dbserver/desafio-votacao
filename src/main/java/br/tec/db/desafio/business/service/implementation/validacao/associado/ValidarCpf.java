package br.tec.db.desafio.business.service.implementation.validacao.associado;


import br.tec.db.desafio.api.v1.dto.associado.AssociadoRequestV1;
import br.tec.db.desafio.api.v1.dto.associado.client.AssociadoClientRequestV1;
import br.tec.db.desafio.business.domain.Associado;
import br.tec.db.desafio.exception.BusinessException;


public class ValidarCpf {



    public void validar(AssociadoRequestV1 dado) {
            if(dado.getCpf().length() < 11){
                throw new BusinessException("CPF com menos de 11 dígitos");
            }
            if(dado.getCpf().length() > 11){
                throw new BusinessException("CPF com mais de 11 dígitos");
            }
        }

    public void validar(AssociadoClientRequestV1 dado) {
        if(dado.getCpf().length() < 11){
            throw new BusinessException("CPF com menos de 11 dígitos");
        }
        if(dado.getCpf().length() > 11){
            throw new BusinessException("CPF com mais de 11 dígitos");
        }
    }


    }



