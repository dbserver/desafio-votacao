package br.tec.db.desafio.business.service.implementation.validacao.sessao.totalvotos;

import br.tec.db.desafio.business.domain.Sessao;
import br.tec.db.desafio.business.service.implementation.validacao.sessao.AValidacaoTotalDeVotosDaSessao;
import br.tec.db.desafio.exception.BusinessException;
import lombok.NoArgsConstructor;


public class ValidarTotalSessaoComPoucoCaracter extends AValidacaoTotalDeVotosDaSessao {


    public void validarSessao(Sessao sessao, Long id) {
        if(sessao.getPauta().getAssunto().length() < 3){
            throw new BusinessException("Não pode ter menos de 3 caracteres");
        }
    }




}
