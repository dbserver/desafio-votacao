package br.com.dbserver.votacao.v1.controller;

import br.com.dbserver.votacao.v1.dto.request.VotoRequest;
import br.com.dbserver.votacao.v1.dto.response.VotoResponse;
import br.com.dbserver.votacao.v1.service.VotoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Log4j2
@AllArgsConstructor
@RequestMapping("v1/voto")
@RestController
public class VotoController {

	private final VotoService votoService;

	@Operation(operationId = "votar",
			summary = "Cadastra um novo voto",
			tags = {"voto"},
			parameters = {@Parameter()},
			responses = {
					@ApiResponse(responseCode = "201",
							description = "Operação bem Sucedida",
							content = @Content(schema = @Schema(implementation = VotoResponse.class))),
					@ApiResponse(responseCode = "500", description = "Dados enviados podem ser inválidos")
			})
	@PostMapping()
	public ResponseEntity<VotoResponse> votar(@RequestBody @Valid VotoRequest votoRequest) {
		log.info("Metodo: votar - Pauta: " + votoRequest.getPautaId()
				+ " - Associado: " + votoRequest.getDocumentoAssociado());
		return new ResponseEntity<>(votoService.salvar(votoRequest), HttpStatus.CREATED);
	}


}
