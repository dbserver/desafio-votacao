package br.tec.db.desafio.api.client;

import br.tec.db.desafio.api.v1.dto.associado.AssociadoMapperV1;
import br.tec.db.desafio.api.v1.dto.associado.client.AssociadoClientRequestV1;
import br.tec.db.desafio.api.v1.dto.associado.client.AssociadoClientResponseV1;
import br.tec.db.desafio.business.domain.enums.StatusCpf;

import br.tec.db.desafio.business.service.implementation.validacao.FactoryValidacao;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;


@Component
public class AssociadoStatusCpfClient {


    private static final FactoryValidacao valida = new FactoryValidacao();


    @RequestMapping(value = "/statuscpfclient", method = RequestMethod.POST)
    public AssociadoClientResponseV1 getStatusCpfAssociado(
            @RequestBody AssociadoClientRequestV1 associadoClientRequestV1
            ) {
        valida.validarCpf(associadoClientRequestV1);
        return AssociadoMapperV1.statusCpfToAssociadoClientResponseV1(
                StatusCpf.randomStatusCpf()
        );
    }


}
