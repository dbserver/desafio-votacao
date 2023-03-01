package br.tec.db.desafio.business.service.implementation.validacao.sessao.votar;


import br.tec.db.desafio.exception.BusinessException;
import lombok.NoArgsConstructor;


import java.time.LocalDateTime;

public class ValidarSessaoExpirada {


    public void validar(LocalDateTime duracao) {
        if(duracao.isBefore(LocalDateTime.now())){
            throw new BusinessException("Sessão expirada");
        }
    }
}
