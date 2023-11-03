package com.voting.resources;

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
@RequestMapping("/votes")
public class VoteResource {

	@Autowired
	VoteService voteService;

	@PostMapping("/{topicId}/vote")
	public ResponseEntity vote(@PathVariable("topicId") Integer topicId, @RequestBody Vote vote) {
		voteService.vote(topicId, vote);

		return ResponseEntity.ok().build();
	}

}
