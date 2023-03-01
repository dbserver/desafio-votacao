package br.tec.db.desafio.business.service.implementation.validacao.pauta;

import br.tec.db.desafio.exception.BusinessException;


public class ValidarPautaComPoucoCaracter  {



    public void validar(String dado) {

            if(dado.length() > 3){
                throw new BusinessException("Pauta muito curta, descreva com mais detalhes");
            }

    }
}
