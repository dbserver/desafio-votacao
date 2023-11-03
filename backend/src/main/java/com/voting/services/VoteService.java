package com.voting.services;

import java.time.Instant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.voting.entities.Session;
import com.voting.entities.Vote;
import com.voting.repositories.VoteRepository;
import com.voting.services.exceptions.BadRequestException;
import com.voting.services.exceptions.ResourceNotFoundException;

@Service
public class VoteService {

	@Autowired
	private VoteRepository voteRepository;

	@Autowired
	private TopicService topicService;

	@Autowired
	private SessionService sessionService;

	public void vote(Integer topicId, Vote vote) {
		Session session = sessionService.findByTopic(topicService.findById(topicId))
				.orElseThrow(() -> new ResourceNotFoundException("A sessão da votação não foi encontrada!"));

		if (Instant.now().isAfter(session.getClosingDate())) {
			throw new BadRequestException("A sessão já foi encerrada!");
		}

		vote.setSession(session);
		vote.setMoment(Instant.now());

		if (voteRepository.existsByVotingSessionAndCpfVoter(session, vote.getCpfVoter())) {
			throw new BadRequestException(
					"Você já registrou o seu voto para esta pauta, é permitido apenas um voto por pessoa!");
		}

		voteRepository.save(vote);
	}

}
