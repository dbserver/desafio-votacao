package com.voting.resources;

import java.net.URI;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.voting.entities.Topic;
import com.voting.services.TopicService;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping(value = "/v1/topics")
public class TopicResource {

	private static final Logger logger = LoggerFactory.getLogger(TopicResource.class);

	@Autowired
	private TopicService topicService;

	@Operation(summary = "Busca todas as pautas cadastradas", method = "GET")
	@GetMapping
	public ResponseEntity<List<Topic>> findAll() {
		logger.info("Consultando pautas...");
		List<Topic> list = topicService.findAll();

		return ResponseEntity.ok().body(list);
	}

	@Operation(summary = "Busca uma pauta pelo Id", method = "GET")
	@GetMapping(value = "/{id}")
	public ResponseEntity<Topic> findById(@PathVariable Integer id) {
		logger.info("Consultando pauta...");
		Topic topic = topicService.findById(id);

		return ResponseEntity.ok().body(topic);
	}

	@Operation(summary = "Cria uma nova pauta", method = "POST")
	@PostMapping
	public ResponseEntity<Topic> save(@RequestBody Topic topic) {
		logger.info("Registrando nova pauta...");
		topic = topicService.save(topic);
		logger.info("Nova pauta registrada...");

		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(topic.getId()).toUri();

		return ResponseEntity.created(uri).body(topic);
	}

	@Operation(summary = "Atualiza uma pauta", method = "PUT")
	@PutMapping(value = "/{id}")
	public ResponseEntity<Topic> update(@PathVariable Integer id, @RequestBody Topic topic) {
		logger.info("Atualizando pauta...");
		topic = topicService.update(id, topic);
		logger.info("Pauta atualizada...");

		return ResponseEntity.ok().body(topic);
	}

	@Operation(summary = "Apaga uma pauta", method = "DELETE")
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		logger.info("Deletando pauta...");
		topicService.delete(id);
		logger.info("Pauta deletada...");

		return ResponseEntity.noContent().build();
	}

}
