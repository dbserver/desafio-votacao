package com.voting.resources;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.voting.entities.Vote;
import com.voting.services.VoteService;

@RestController
@RequestMapping("/v1/votes")
public class VoteResource {

	private static final Logger logger = LoggerFactory.getLogger(VoteResource.class);

	@Autowired
	VoteService voteService;

	@PostMapping("/{topicId}/vote")
	public ResponseEntity vote(@PathVariable("topicId") Integer topicId, @RequestBody Vote vote) {
		logger.info("Registrando voto...");
		voteService.vote(topicId, vote);
		logger.info("Voto registrado...");

		return ResponseEntity.ok().build();
	}

}
