package com.voting.resources;

import java.util.List;

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

@RestController
@RequestMapping(value = "/v1/sessions")
public class SessionResource {

	@Autowired
	SessionService sessionService;

	@GetMapping
	public ResponseEntity<List<Session>> findAll(@RequestParam String showClosedSessions) {
		List<Session> list = sessionService.findAll(showClosedSessions);

		return ResponseEntity.ok().body(list);
	}

	@PostMapping("/{topicId}/start-voting-session")
	public ResponseEntity startVotingSession(@PathVariable("topicId") Integer topicId,
			@RequestParam Integer minutesVoting) {
		sessionService.startVotingSession(topicId, minutesVoting);

		return ResponseEntity.ok().build();
	}

}
