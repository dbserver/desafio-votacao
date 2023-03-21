package com.desafio.votacao.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.desafio.votacao.dto.VotacaoDTO;
import com.desafio.votacao.dto.VotoDTO;
import com.desafio.votacao.enums.VotoEnumDTO;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

public class SessaoVotacaoController {
    
    @ApiResponse(responseCode = "201", description = "Sucesso")
    ResponseEntity<Void> votar(@RequestBody VotoDTO votoDTO);

	@ApiResponse(responseCode = "200", description = "Sucesso")
	ResponseEntity<List<VotacaoDTO>> consultar(@RequestParam Long idPauta, @RequestParam Optional<Long> idAssociado,
			@RequestParam Optional<VotoEnumDTO> voto);
}
