package com.voting.resources;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.voting.entities.Topic;
import com.voting.services.TopicService;

@RestController
@RequestMapping(value = "/topics")
public class TopicResource {

	@Autowired
	private TopicService topicService;

	@GetMapping
	public ResponseEntity<List<Topic>> findAll() {
		List<Topic> list = topicService.findAll();

		return ResponseEntity.ok().body(list);
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<Topic> findById(@PathVariable Integer id) {
		Topic topic = topicService.findById(id);

		return ResponseEntity.ok().body(topic);
	}

	@PostMapping
	public ResponseEntity<Topic> save(@RequestBody Topic topic) {
		topic = topicService.save(topic);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(topic.getId()).toUri();

		return ResponseEntity.created(uri).body(topic);
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<Topic> update(@PathVariable Integer id, @RequestBody Topic topic) {
		topic = topicService.update(id, topic);
		return ResponseEntity.ok().body(topic);
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		topicService.delete(id);
		return ResponseEntity.noContent().build();
	}

}
