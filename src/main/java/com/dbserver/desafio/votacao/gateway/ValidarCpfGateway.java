package com.dbserver.desafio.votacao.gateway;

import com.dbserver.desafio.votacao.gateway.dto.CpfStatusRespostaDTO;

public interface ValidarCpfGateway {

    CpfStatusRespostaDTO execute(String cpfAssociado);
}
