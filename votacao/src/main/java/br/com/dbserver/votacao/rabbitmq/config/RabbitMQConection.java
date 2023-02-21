package br.com.dbserver.votacao.rabbitmq.config;

import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class RabbitMQConection {

	private final AmqpAdmin amqpAdmin;

	public RabbitMQConection(AmqpAdmin amqpAdmin) {
		this.amqpAdmin = amqpAdmin;
	}

	private Queue fila(String nomeFila) {
		return new Queue(nomeFila, true, false, false);
	}

	private DirectExchange trocaDireta(String exchange) {
		return new DirectExchange(exchange);
	}

	private Binding relacionamento(Queue fila, DirectExchange troca) {
		return new Binding(fila.getName(), Binding.DestinationType.QUEUE, troca.getName(), fila.getName(), null);
	}

	@PostConstruct
	private void adiciona() {
		Queue filaVoto = this.fila(RabbitMQConstantes.FILA_VOTO);

		DirectExchange exchange = this.trocaDireta(RabbitMQConstantes.EXCHANGE_VOTO);

		Binding ligacaoFilaComExchange = this.relacionamento(filaVoto, exchange);

		this.amqpAdmin.declareQueue(filaVoto);

		this.amqpAdmin.declareExchange(exchange);

		this.amqpAdmin.declareBinding(ligacaoFilaComExchange);
	}
}