package br.tec.db.desafio.api.v1.dto.associado;

import br.tec.db.desafio.api.v1.dto.associado.client.AssociadoClientResponseV1;
import br.tec.db.desafio.business.domain.Associado;
import br.tec.db.desafio.business.domain.enums.StatusCpf;
import br.tec.db.desafio.exception.BusinessException;

public class AssociadoMapperV1 {
    private AssociadoMapperV1(){}
    public static AssociadoResponseV1 associadoToAssociadoResponseV1(Associado source) {
        if (source == null) {
            throw new BusinessException("Dados de response inexistentes");
        }
        AssociadoResponseV1 associadoResponseV1 =new AssociadoResponseV1();
        associadoResponseV1.setCpf(source.getCpf());
        associadoResponseV1.setNome(source.getNome());
        return associadoResponseV1;

    }

    public static Associado associadoRequestV1ToAssociado(AssociadoRequestV1 source) {
        if (source == null) {
            throw new BusinessException("Dados de request inexistentes");
        }
        Associado associado =new Associado();
        associado.setCpf(source.getCpf());
        associado.setNome(source.getNome());

        return associado;

    }

    public static AssociadoClientResponseV1 statusCpfToAssociadoClientResponseV1(StatusCpf source) {
        if (source == null) {
            throw new BusinessException("Dados de response inexistentes");
        }
        AssociadoClientResponseV1 associado =new AssociadoClientResponseV1();
        associado.setStatusCpf(source);

        return associado;
    }
}
