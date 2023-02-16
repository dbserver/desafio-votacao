package br.tec.db.desafio.api.v1.dto.sessao;

import br.tec.db.desafio.api.v1.dto.sessao.request.SessaoParaCriarRequestV1;
import br.tec.db.desafio.api.v1.dto.sessao.request.SessaoParaSaberTotalVotosRequestV1;
import br.tec.db.desafio.api.v1.dto.sessao.request.SessaoParaVotarRequestV1;
import br.tec.db.desafio.api.v1.dto.sessao.response.SessaoCriadaResponseV1;
import br.tec.db.desafio.api.v1.dto.sessao.response.SessaoTotalVotosResponseV1;
import br.tec.db.desafio.api.v1.dto.sessao.response.SessaoVotadaResponseV1;
import br.tec.db.desafio.business.domain.Pauta;
import br.tec.db.desafio.business.domain.Sessao;
import br.tec.db.desafio.exception.BusinessException;

public class SessaoMapperV1 {
    private SessaoMapperV1(){}
    public static SessaoCriadaResponseV1 sessaoToSessaoCriadaResponseV1(Sessao source) {
        if (source == null) {
            throw new BusinessException("Dados inexistentes");
        }
        SessaoCriadaResponseV1 sessaoResponseV1 =new SessaoCriadaResponseV1();
        sessaoResponseV1.setAssuntoPauta(source.getPauta().getAssunto());

        return sessaoResponseV1;

    }

    public static SessaoTotalVotosResponseV1 sessaoToSessaoTotalVotosResponseV1(Sessao source) {
        if (source == null) {
            throw new BusinessException("Dados inexistentes");
        }
        SessaoTotalVotosResponseV1 sessaoTotalVotosResponseV1 =new SessaoTotalVotosResponseV1();
        sessaoTotalVotosResponseV1.setTotalVotos(source.getTotalVotos());

        return sessaoTotalVotosResponseV1;

    }

    public static SessaoVotadaResponseV1 sessaoToSessaoVotadaResponseV1(Sessao source) {
        if (source == null) {
            throw new BusinessException("Dados inexistentes");
        }
        SessaoVotadaResponseV1 sessaoVotadaResponseV1 =new SessaoVotadaResponseV1();
        sessaoVotadaResponseV1.setVoto(source.getVoto());

        return sessaoVotadaResponseV1;

    }

    public static Sessao sessaoParaVotarRequestV1ToSessao(SessaoParaVotarRequestV1 source) {
        if (source == null) {
            throw new BusinessException("Dados inexistentes");
        }
        Sessao sessao =new Sessao();

        sessao.setPauta(new Pauta(source.getAssuntoPauta()));
        sessao.setVoto(source.getVoto());

        return sessao;

    }

    public static Sessao sessaoParaCriarRequestV1ToSessao(SessaoParaCriarRequestV1 source) {
        if (source == null) {
            throw new BusinessException("Dados inexistentes");
        }
        Sessao sessao =new Sessao();

        sessao.setPauta(new Pauta(source.getAssuntoPauta()));

        return sessao;

    }

    public static Sessao sessaoParaSaberTotalVotosRequestV1ToSessao(SessaoParaSaberTotalVotosRequestV1 source) {
        if (source == null) {
            throw new BusinessException("Dados inexistentes");
        }
        Sessao sessao =new Sessao();

        sessao.setPauta(new Pauta(source.getAssuntoPauta()));

        return sessao;
    }
}
