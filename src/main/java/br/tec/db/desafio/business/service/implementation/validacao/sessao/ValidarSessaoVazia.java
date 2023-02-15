package br.tec.db.desafio.business.service.implementation.validacao.sessao;

import br.tec.db.desafio.api.v1.dto.pauta.PautaRequestV1;
import br.tec.db.desafio.api.v1.dto.sessao.SessaoRequestV1;
import br.tec.db.desafio.exception.BusinessException;

public class ValidarSessaoVazia implements ValidacaoSessao {
    @Override
    public void validarSessao(SessaoRequestV1 sessaoRequestV1) {
        if(sessaoRequestV1.getAssuntoPauta().isEmpty()){
            throw new BusinessException("A pauta não pode estar vazia");
        }
    }
}
