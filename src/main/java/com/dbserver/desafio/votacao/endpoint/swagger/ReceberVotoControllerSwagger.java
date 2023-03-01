package com.dbserver.desafio.votacao.endpoint.swagger;

import com.dbserver.desafio.votacao.endpoint.dto.VotoDTO;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;


public interface ReceberVotoControllerSwagger {

    @Operation(summary = "Receber os votos para a Pauta")
    ResponseEntity<VotoDTO> receberVoto(@RequestBody VotoDTO votoDTO);
}

