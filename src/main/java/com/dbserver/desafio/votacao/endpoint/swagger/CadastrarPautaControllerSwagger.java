package com.dbserver.desafio.votacao.endpoint.swagger;

import com.dbserver.desafio.votacao.endpoint.dto.PautaDTO;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

public interface CadastrarPautaControllerSwagger {

    @Operation(summary = "Cadastrar Pauta para Assembleia")
    ResponseEntity<PautaDTO> cadastrarPauta(@RequestBody PautaDTO pautaDTO);
}
