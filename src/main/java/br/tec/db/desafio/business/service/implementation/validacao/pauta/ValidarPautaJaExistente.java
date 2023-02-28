package br.tec.db.desafio.business.service.implementation.validacao.pauta;

import br.tec.db.desafio.business.domain.Pauta;
import br.tec.db.desafio.exception.BusinessException;
import lombok.NoArgsConstructor;


public class ValidarPautaJaExistente extends AValidacaoCriarUmaNovaPauta {


    @Override
    public void validarPauta(Pauta pauta) {
        if(pauta!=null){
            throw new BusinessException("Já existe uma pauta com este tema");
        }
    }
}
