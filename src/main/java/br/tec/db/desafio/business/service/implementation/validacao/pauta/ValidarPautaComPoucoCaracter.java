package br.tec.db.desafio.business.service.implementation.validacao.pauta;

import br.tec.db.desafio.business.domain.Pauta;
import br.tec.db.desafio.exception.BusinessException;
import lombok.NoArgsConstructor;


public class ValidarPautaComPoucoCaracter extends AValidacaoCriarUmaNovaPauta {


    @Override
    public void validarPauta(Pauta pauta) {
        if(pauta.getAssunto().length() > 3){
            throw new BusinessException("Pauta muito curta, descreva com mais detalhes");
        }
    }
}
