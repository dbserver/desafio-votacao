package br.com.dbserver.votacao.rabbitmq.controller;


import br.com.dbserver.votacao.rabbitmq.config.RabbitMQConstantes;
import br.com.dbserver.votacao.rabbitmq.service.VotoProducer;
import br.com.dbserver.votacao.v1.dto.request.VotoRequest;
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
@RequestMapping("v1/fila/voto")
@RestController
public class VotoProducerController {

	private final VotoProducer votoProducer;

	@Operation(operationId = "votar",
			summary = "Cadastra um novo voto enviando a uma fila primeiro",
			tags = {"rabbitmq"},
			parameters = {@Parameter()},
			responses = {
					@ApiResponse(responseCode = "201",
							description = "Operação bem Sucedida",
							content = @Content(schema = @Schema(implementation = String.class))),
					@ApiResponse(responseCode = "500", description = "Dados enviados podem ser inválidos")
			})
	@PostMapping()
	public ResponseEntity<String> votar(@RequestBody @Valid VotoRequest votoRequest) {
		log.info("Metodo: votar");
		String mensagem = "Voto Enviado Para a Fila: " + RabbitMQConstantes.FILA_VOTO;

		votoProducer.produzir(RabbitMQConstantes.FILA_VOTO, votoRequest);
		return new ResponseEntity<>(mensagem, HttpStatus.CREATED);
	}

}
