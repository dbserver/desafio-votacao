package com.voting.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.voting.entities.Topic;
import com.voting.repositories.TopicRepository;
import com.voting.services.exceptions.ResourceNotFoundException;

import jakarta.persistence.EntityNotFoundException;

@Service
public class TopicService {

	@Autowired
	private TopicRepository topicRepository;

	public List<Topic> findAll() {
		return topicRepository.findAll();
	}

	public Topic findById(Integer id) {

		Optional<Topic> topic = topicRepository.findById(id);

		return topic.orElseThrow(() -> new ResourceNotFoundException("A pauta não foi encontrada!"));
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
