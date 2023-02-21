package br.com.dbserver.votacao.rabbitmq.service;

import br.com.dbserver.votacao.v1.dto.request.VotoRequest;
import br.com.dbserver.votacao.v1.exception.BadRequestException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Log4j2
@Service
@AllArgsConstructor
public class VotoProducer {

	private final RabbitTemplate rabbitTemplate;
	private final ObjectMapper objectMapper;

	public void produzir(String nomeFila, VotoRequest votoRequest) {
		log.info("Metodo: enviaMensagem - FILA: " + nomeFila);
		try {
			String mensagemJson = this.objectMapper.writeValueAsString(votoRequest);
			this.rabbitTemplate.convertAndSend(nomeFila, mensagemJson);
		} catch (Exception e) {
			e.printStackTrace();
			throw new BadRequestException("Erro ao enviar Mensagem para a fila: " + nomeFila);
		}
	}
}