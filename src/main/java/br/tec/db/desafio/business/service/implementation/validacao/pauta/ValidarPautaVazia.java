package br.tec.db.desafio.business.service.implementation.validacao.pauta;

import br.tec.db.desafio.business.domain.Pauta;
import br.tec.db.desafio.exception.BusinessException;
import lombok.NoArgsConstructor;


public class ValidarPautaVazia extends AValidacaoCriarUmaNovaPauta {


    @Override
    public void validarPauta(Pauta pauta) {
        if(pauta==null){
            throw new BusinessException("Pauta inexistente");
        }
        if(pauta.getAssunto().isEmpty() || pauta.getAssunto().isBlank()){
            throw new BusinessException("Pauta vazia");
        }
    }
}
