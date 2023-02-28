package com.dbserver.desafio.votacao.endpoint.swagger;

import com.dbserver.desafio.votacao.endpoint.dto.PautaDTO;
import com.dbserver.desafio.votacao.endpoint.dto.VotosPautaDTO;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

public interface ContabilizarVotosControllerSwagger {

    @Operation(summary = "Contabilizar os votos de uma Pauta")
    ResponseEntity<VotosPautaDTO> contabilizarVotos(@RequestBody PautaDTO pautaDTO);
}
