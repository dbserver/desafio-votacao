package com.voting.services;

import java.time.Instant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.voting.entities.Session;
import com.voting.entities.Vote;
import com.voting.repositories.VoteRepository;

@Service
public class VoteService {

	@Autowired
	private VoteRepository voteRepository;

	@Autowired
	private TopicService topicService;

	@Autowired
	private SessionService sessionService;

	public void vote(Integer topicId, Vote vote) {
		Session session = sessionService.findByTopic(topicService.findById(topicId));

		vote.setSession(session);
		vote.setMoment(Instant.now());

		voteRepository.save(vote);
	}

}
