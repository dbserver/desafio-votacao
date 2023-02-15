package br.tec.db.desafio.api.v1.dto.sessao;

import br.tec.db.desafio.business.entity.Pauta;
import br.tec.db.desafio.business.entity.Sessao;
import br.tec.db.desafio.exception.BusinessException;

public class SessaoMapperV1 {
    private SessaoMapperV1(){}
    public static SessaoResponseV1 sessaoToSessaoResponseV1(Sessao source) {
        if (source == null) {
            throw new BusinessException("Dados inexistentes");
        }
        SessaoResponseV1 sessaoResponseV1 =new SessaoResponseV1();
        sessaoResponseV1.setAssuntoPauta(source.getPauta().getAssunto());

        return sessaoResponseV1;

    }

    public static Sessao sessaoRequestV1ToSessao(SessaoRequestV1 source) {
        if (source == null) {
            throw new BusinessException("Dados inexistentes");
        }
        Sessao sessao =new Sessao();

        sessao.setPauta(new Pauta(source.getAssuntoPauta()));

        return sessao;

    }
}
