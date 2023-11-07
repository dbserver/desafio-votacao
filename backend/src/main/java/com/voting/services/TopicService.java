package com.voting.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.voting.entities.Session;
import com.voting.entities.Topic;
import com.voting.entities.Vote;
import com.voting.repositories.SessionRepository;
import com.voting.repositories.TopicRepository;
import com.voting.services.exceptions.ResourceNotFoundException;

import jakarta.persistence.EntityNotFoundException;

@Service
public class TopicService {

	@Autowired
	private TopicRepository topicRepository;

	@Autowired
	private SessionRepository sessionRepository;

	private Session findSessionByTopic(Topic topic) {
		Optional<Session> session = sessionRepository.findByTopic(topic);

		return session.orElseThrow(() -> new ResourceNotFoundException("Sessão não encontrada para a pauta!"));
	}

	public List<Topic> findAll() {
		return topicRepository.findAll();
	}

	public Topic findById(Integer id) {

		Optional<Topic> topic = topicRepository.findById(id);

		return topic.orElseThrow(() -> new ResourceNotFoundException("A pauta não foi encontrada!"));
	}

	public Topic getTopicResult(Integer id) {
		Topic topic = findById(id);
		Session session = findSessionByTopic(topic);

		topic.setResult(getResult(session.getVotes()));

		return topic;
	}

	public Map<String, Long> getResult(Set<Vote> votes) {
		Map<String, Long> result = new HashMap<>();
		result.put("SIM", votes.stream().filter(v -> v.getVoteMessage().toString().equalsIgnoreCase("SIM")).count());
		result.put("NAO", votes.stream().filter(v -> v.getVoteMessage().toString().equalsIgnoreCase("NAO")).count());

		return result;
	}

	public Topic save(Topic topic) {
		return topicRepository.save(topic);
	}

	public Topic update(Integer id, Topic topic) {
		try {

			Topic entity = topicRepository.getReferenceById(id);
			updateData(entity, topic);
			return topicRepository.save(entity);

		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException("A pauta não foi encontrada!");
		}
	}

	public void delete(Integer id) {
		topicRepository.deleteById(id);
	}

	private void updateData(Topic topic, Topic obj) {
		topic.setName(obj.getName());
	}

}
