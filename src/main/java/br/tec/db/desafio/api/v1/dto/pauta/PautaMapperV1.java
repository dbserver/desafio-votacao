package br.tec.db.desafio.api.v1.dto.pauta;

import br.tec.db.desafio.business.domain.Pauta;
import br.tec.db.desafio.exception.BusinessException;

public class PautaMapperV1 {
    private PautaMapperV1(){}
    public static PautaResponseV1 pautaToPautaResponseV1(Pauta source) {
        if (source == null) {
            throw new BusinessException("Dados inexistentes");
        }
        PautaResponseV1 pautaResponseV1 =new PautaResponseV1();
        pautaResponseV1.setAssunto(source.getAssunto());
        return pautaResponseV1;

    }

    public static Pauta pautaRequestV1ToPauta(PautaRequestV1 source) {
        if (source == null) {
            throw new BusinessException("Dados inexistentes");
        }
        Pauta pauta =new Pauta();
        pauta.setAssunto(source.getAssunto());
        return pauta;

    }
}
