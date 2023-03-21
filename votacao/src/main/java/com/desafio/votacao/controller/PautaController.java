package com.desafio.votacao.controller;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.desafio.votacao.dto.PautaBasicoDTO;
import com.desafio.votacao.dto.PautaCompletoDTO;

import io.swagger.v3.oas.annotations.responses.ApiResponse;

public interface PautaController {

	@ApiResponse(responseCode = "201", description = "Sucesso")
	ResponseEntity<Void> criar(
			@RequestBody PautaBasicoDTO pautaBasicoDTO);

	@ApiResponse(responseCode = "200", description = "Sucesso")
	ResponseEntity<Void> alterar(
			@PathVariable(value = "id") Long id,
			@RequestBody PautaBasicoDTO pautaBasicoDTO);

	@ApiResponse(responseCode = "200", description = "Sucesso")
	ResponseEntity<List<PautaCompletoDTO>> consultar(
			@RequestParam Optional<String> id,
			@RequestParam Optional<String> descricao,
			@RequestParam Optional<LocalDateTime> dtInicio,
			@RequestParam Optional<LocalDateTime> dtFim,
			Optional<Boolean> excluida);

	@ApiResponse(responseCode = "200", description = "Sucesso")
	ResponseEntity<Void> excluir(
			@PathVariable(value = "id") Long id);
}
