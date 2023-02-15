package br.tec.db.desafio.business.service.implementation.validacao.sessao;

import br.tec.db.desafio.api.v1.dto.pauta.PautaRequestV1;
import br.tec.db.desafio.api.v1.dto.sessao.SessaoRequestV1;
import br.tec.db.desafio.exception.BusinessException;

public class ValidarSessaoComPoucoCaracter implements ValidacaoSessao {
    @Override
    public void validarSessao(SessaoRequestV1 sessaoRequestV1) {
        if(sessaoRequestV1.getAssuntoPauta().length() < 3){
            throw new BusinessException("Não pode ter menos de 3 caracteres");
        }
    }
}
