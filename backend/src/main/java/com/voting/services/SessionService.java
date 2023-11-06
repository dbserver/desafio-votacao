package com.voting.services;

import java.time.Instant;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.voting.entities.Session;
import com.voting.entities.Topic;
import com.voting.repositories.SessionRepository;
import com.voting.services.exceptions.BadRequestException;

@Service
public class SessionService {

	@Autowired
	private SessionRepository sessionRepository;

	@Autowired
	private TopicService topicService;

	public List<Session> findAll(String showClosedSessions) {

		if ("Y".equals(showClosedSessions)) {
			List<Session> sessions = sessionRepository.findClosedSessions(Instant.now());

			for (Session session : sessions) {
				session.setResult(topicService.getResult(session.getVotes()));
			}

			return sessions;
		} else {
			return sessionRepository.findOpenSessions(Instant.now());
		}
	}

	public void startVotingSession(Integer topicId, Integer minutesVoting) {
		Topic topic = topicService.findById(topicId);

		if (Objects.requireNonNull(findByTopic(topic)).isPresent()) {
			throw new BadRequestException("Já existe uma sessão cadastrada para essa pauta!");
		}

		createSession(topic, closingDate(minutesVoting));
	}

	public Optional<Session> findByTopic(Topic topic) {

		return sessionRepository.findByTopic(topic);

	}

	private void createSession(Topic topic, Instant closingDate) {
		Session session = new Session(Instant.now(), closingDate, topic);

		sessionRepository.save(session);
	}

	private Instant closingDate(Integer minutesVoting) {
		return minutesVoting != null && minutesVoting > 0 ? Instant.now().plusSeconds(minutesVoting * 60)
				: Instant.now().plusSeconds(60);
	}

}
