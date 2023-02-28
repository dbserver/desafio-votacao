package br.tec.db.desafio.business.service.implementation.validacao.sessao.votar;


import br.tec.db.desafio.business.domain.Sessao;
import br.tec.db.desafio.business.service.implementation.validacao.sessao.AValidacaoVotarEmUmaSessao;
import br.tec.db.desafio.exception.BusinessException;
import lombok.NoArgsConstructor;


public class ValidarVotarSessaoComPoucoCaracter extends AValidacaoVotarEmUmaSessao {



    public void validarSessao(Sessao sessao) {
        if(sessao.getPauta().getAssunto().length() < 3){
            throw new BusinessException("Não pode ter menos de 3 caracteres");
        }
    }



}
