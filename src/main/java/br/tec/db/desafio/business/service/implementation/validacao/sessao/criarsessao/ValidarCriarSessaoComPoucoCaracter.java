package br.tec.db.desafio.business.service.implementation.validacao.sessao.criarsessao;

import br.tec.db.desafio.business.domain.Sessao;
import br.tec.db.desafio.business.service.implementation.validacao.sessao.AValidacaoCriarUmaNovaSessao;
import br.tec.db.desafio.exception.BusinessException;
import lombok.NoArgsConstructor;


public class ValidarCriarSessaoComPoucoCaracter extends AValidacaoCriarUmaNovaSessao {


    public void validarSessao(Sessao sessao) {
        if(sessao.getPauta().getAssunto().length() < 3){
            throw new BusinessException("Não pode ter menos de 3 caracteres");
        }

    }


}
