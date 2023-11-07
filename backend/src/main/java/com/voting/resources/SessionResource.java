package com.voting.resources;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.voting.entities.Session;
import com.voting.services.SessionService;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping(value = "/v1/sessions")
public class SessionResource {

	private static final Logger logger = LoggerFactory.getLogger(SessionResource.class);

	@Autowired
	SessionService sessionService;

	@Operation(summary = "Busca as sessões", method = "GET")
	@GetMapping
	public ResponseEntity<List<Session>> findAll(@RequestParam String showClosedSessions) {
		logger.info("Consultando sessões...");
		List<Session> list = sessionService.findAll(showClosedSessions);

		return ResponseEntity.ok().body(list);
	}

	@Operation(summary = "Inicia uma sessão de votação", method = "POST")
	@PostMapping("/{topicId}/start-voting-session")
	public ResponseEntity startVotingSession(@PathVariable("topicId") Integer topicId,
			@RequestParam Integer minutesVoting) {
		logger.info("Iniciando votação...");
		sessionService.startVotingSession(topicId, minutesVoting);
		logger.info("Votação iniciada...");

		return ResponseEntity.ok().build();
	}

}
