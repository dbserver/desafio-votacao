package com.dbserver.desafio.votacao.gateway.impl;

import com.dbserver.desafio.votacao.gateway.ValidarCpfGateway;
import com.dbserver.desafio.votacao.gateway.client.ValidaCpfClient;
import com.dbserver.desafio.votacao.gateway.dto.CpfStatusRespostaDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.function.Supplier;

@Service
@Slf4j
@RequiredArgsConstructor
public class ValidarCpfGatewayImpl implements ValidarCpfGateway {

    private final ValidaCpfClient validaCpfClient;

    @Override
    public CpfStatusRespostaDTO execute(String cpfAssociado) {


        return callClient(() -> {

            final ResponseEntity<CpfStatusRespostaDTO> cpfStatusRespostaDTO =validaCpfClient.validaClient(cpfAssociado);
            return cpfStatusRespostaDTO.getBody();
        });
    }
    private CpfStatusRespostaDTO callClient(final Supplier<CpfStatusRespostaDTO> getCpfAssociado) {
        return getCpfAssociado.get();
    }
}
