package br.com.dbserver.votacao.rabbitmq.service;

import br.com.dbserver.votacao.rabbitmq.config.RabbitMQConstantes;
import br.com.dbserver.votacao.v1.dto.request.VotoRequest;
import br.com.dbserver.votacao.v1.service.VotoService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Log4j2
@AllArgsConstructor
@Component
public class VotoConsumer {

	VotoService votoService;

	@RabbitListener(queues = RabbitMQConstantes.FILA_VOTO)
	private void consumidor(String mensagem) throws JsonProcessingException {
		log.info("Metodo: consumidor - FILA: " + RabbitMQConstantes.FILA_VOTO);
		VotoRequest votoRequest = new ObjectMapper().readValue(mensagem, VotoRequest.class);
		votoService.salvar(votoRequest);
	}
}
