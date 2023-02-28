package com.dbserver.desafio.votacao.endpoint.swagger;

import com.dbserver.desafio.votacao.endpoint.constant.EndpointURL;
import com.dbserver.desafio.votacao.endpoint.dto.VotoDTO;
import com.dbserver.desafio.votacao.endpoint.mapper.VotoDtoParaVotoMapper;
import com.dbserver.desafio.votacao.endpoint.mapper.VotoParaVotoDTOMapper;
import com.dbserver.desafio.votacao.usecase.assembleia.ReceberVotoUseCase;
import com.dbserver.desafio.votacao.usecase.domain.Voto;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


public interface ReceberVotoControllerSwagger {

    @Operation(summary = "Receber os votos para a Pauta")
    public ResponseEntity<VotoDTO> receberVoto(@RequestBody VotoDTO votoDTO);
}

