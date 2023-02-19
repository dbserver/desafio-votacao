package br.com.dbserver.votacao.v1.controller;

import br.com.dbserver.votacao.v1.dto.request.PautaRequest;
import br.com.dbserver.votacao.v1.dto.response.AssembleiaPaginadaResponse;
import br.com.dbserver.votacao.v1.dto.response.PautaPaginadaResponse;
import br.com.dbserver.votacao.v1.dto.response.PautaResponse;
import br.com.dbserver.votacao.v1.dto.response.PautaResultadoResponse;
import br.com.dbserver.votacao.v1.service.PautaService;
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
@RequestMapping("v1/pauta")
@RestController
public class PautaController {

	private final PautaService pautaService;

	@Operation(operationId = "criar_pauta",
			summary = "Cadastra uma nova pauta",
			tags = {"pauta"},
			parameters = {@Parameter()},
			responses = {
					@ApiResponse(responseCode = "201",
							description = "Operação bem Sucedida",
							content = @Content(schema = @Schema(implementation = PautaResponse.class))),
					@ApiResponse(responseCode = "500", description = "Dados enviados podem ser inválidos")
			})
	@PostMapping()
	public ResponseEntity<PautaResponse> criarPauta(@RequestBody @Valid PautaRequest pautaRequest) {
		log.info("Metodo: criarPauta - Pauta Descricao: " + pautaRequest.getDescricao());

		return new ResponseEntity<>(pautaService.criarPauta(pautaRequest), HttpStatus.CREATED);
	}

	@Operation(operationId = "resultado_da_pauta",
			summary = "Retorna o resuldado da eleição",
			tags = {"pauta"},
			parameters = {@Parameter()},
			responses = {
					@ApiResponse(responseCode = "200",
							description = "Operação bem Sucedida",
							content = @Content()),
					@ApiResponse(responseCode = "404",
							description = "Pauta não encontrada")
			})
	@GetMapping("/{id}")
	public ResponseEntity<PautaResultadoResponse> buscarPautaPeloId(@PathVariable("id") Long id) {
		log.info("Metodo: buscarPautaPeloId - id: " + id);
		return new ResponseEntity<>(pautaService.buscarPorID(id), HttpStatus.OK);
	}

	@Operation(operationId = "buscar_todas",
			summary = "Busca pautas com paginação",
			tags = {"pauta"},
			parameters = {@Parameter()},
			responses = {
					@ApiResponse(responseCode = "200",
							description = "Operação bem Sucedida",
							content = @Content(schema = @Schema(implementation = AssembleiaPaginadaResponse.class))),
					@ApiResponse(responseCode = "500", description = "Dados enviados podem ser inválidos")

			})
	@GetMapping()
	public ResponseEntity<PautaPaginadaResponse> buscarTodasPautas(@PageableDefault(sort = "id",
			direction = Sort.Direction.ASC,
			page = 0,
			size = 10) Pageable pageable) {
		log.info("Metodo: buscarTodasPautas ");
		return new ResponseEntity<>(pautaService.buscarTodas(pageable), HttpStatus.OK);
	}
}
