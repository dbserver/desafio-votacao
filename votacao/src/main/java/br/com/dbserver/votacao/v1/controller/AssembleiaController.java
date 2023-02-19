package br.com.dbserver.votacao.v1.controller;

import br.com.dbserver.votacao.v1.dto.request.AssembleiaRequest;
import br.com.dbserver.votacao.v1.dto.response.AssembleiaPaginadaResponse;
import br.com.dbserver.votacao.v1.dto.response.AssembleiaResponse;
import br.com.dbserver.votacao.v1.service.AssembleiaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
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

	@Operation(operationId = "cadastrar",
			summary = "Cadastra uma nova Assembleia",
			tags = {"assembleia"},
			parameters = {@Parameter()},
			responses = {
					@ApiResponse(responseCode = "201",
							description = "Operação bem Sucedida",
							content = @Content(schema = @Schema(implementation = AssembleiaResponse.class))),
					@ApiResponse(responseCode = "500", description = "Dados enviados podem ser inválidos")
			})
	@PostMapping()
	public ResponseEntity<AssembleiaResponse> criarAssembleia(
			@RequestBody @Valid AssembleiaRequest assembleiaRequest) {
		log.info("Metodo: criarAssembleia");
		return new ResponseEntity<>(assembleiaService.criar(assembleiaRequest), HttpStatus.CREATED);
	}

	@Operation(operationId = "buscar_todas",
			summary = "Busca assembleias com paginação",
			tags = {"assembleia"},
			parameters = {@Parameter()},
			responses = {
					@ApiResponse(responseCode = "200",
							description = "Operação bem Sucedida",
							content = @Content(schema = @Schema(implementation = AssembleiaPaginadaResponse.class))),
					@ApiResponse(responseCode = "500", description = "Dados enviados podem ser inválidos")

			})
	@GetMapping()
	public ResponseEntity<AssembleiaPaginadaResponse> buscarTodasAssembleias(@PageableDefault(sort = "id",
			direction = Sort.Direction.ASC,
			page = 0,
			size = 10) Pageable pageable) {
		log.info("Metodo: buscarTodasAssembleias ");
		return new ResponseEntity<>(assembleiaService.buscarTodas(pageable), HttpStatus.OK);
	}
}