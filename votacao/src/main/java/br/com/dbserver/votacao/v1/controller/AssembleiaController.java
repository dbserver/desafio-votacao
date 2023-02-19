package br.com.dbserver.votacao.v1.controller;

import br.com.dbserver.votacao.v1.dto.request.AssembleiaRequest;
import br.com.dbserver.votacao.v1.dto.request.AssociadoRequest;
import br.com.dbserver.votacao.v1.dto.response.AssembleiaResponse;
import br.com.dbserver.votacao.v1.dto.response.AssociadoResponse;
import br.com.dbserver.votacao.v1.service.AssembleiaService;
import br.com.dbserver.votacao.v1.service.AssociadoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Log4j2
@AllArgsConstructor
@RequestMapping("v1/assembleia")
@RestController
public class AssembleiaController {

	private final AssembleiaService assembleiaService;


	@PostMapping()
	public ResponseEntity<AssembleiaResponse> criarAssembleia(
			@RequestBody @Valid AssembleiaRequest assembleiaRequest) {
		log.info("Metodo: criarAssembleia");
		return new ResponseEntity<>(assembleiaService.criar(assembleiaRequest), HttpStatus.CREATED);
	}
}