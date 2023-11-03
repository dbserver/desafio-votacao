package com.voting.services;

import java.time.Instant;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.voting.entities.Session;
import com.voting.entities.Topic;
import com.voting.repositories.SessionRepository;

@Service
public class SessionService {

	@Autowired
	private SessionRepository sessionRepository;

	@Autowired
	private TopicService topicService;

	public void startVotingSession(Integer topicId, Integer minutesVoting) {
		Topic topic = topicService.findById(topicId);

		createSession(topic, closingDate(minutesVoting));
	}

	public Session findByTopic(Topic topic) {
		Optional<Session> session = sessionRepository.findByTopic(topic);

		return session.get();
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
