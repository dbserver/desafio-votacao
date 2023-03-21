package com.desafio.votacao.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.desafio.votacao.dto.AssociadoBasicoDTO;
import com.desafio.votacao.dto.AssociadoCompletoDTO;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

public interface VotoAssociadoController {
    
	@ApiResponse(responseCode = "201", description = "Sucesso")
	ResponseEntity<Void> criar(@RequestBody AssociadoBasicoDTO associadoBasicoDTO);

	@ApiResponse(responseCode = "200", description = "Sucesso")
	ResponseEntity<Void> alterar(@PathVariable(value = "id") Long id, @RequestBody AssociadoBasicoDTO associadoBasicoDTO);

	@ApiResponse(responseCode = "200", description = "Sucesso")
	ResponseEntity<List<AssociadoCompletoDTO>> consultar(@RequestParam Optional<String> id, @RequestParam Optional<String> nome, Optional<Boolean> excluido);

	@ApiResponse(responseCode = "200", description = "Sucesso")
	ResponseEntity<Void> excluir(@PathVariable(value = "id") Long id);
}
