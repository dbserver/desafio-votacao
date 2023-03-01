package br.tec.db.desafio.business.service.implementation.validacao.sessao.votar;


import br.tec.db.desafio.exception.BusinessException;
import lombok.NoArgsConstructor;


public class ValidarSessaoJaVotada {



    public void validar(Long id) {
        if(id != null){
            throw new BusinessException("Associado já votou");
        }
    }


}
