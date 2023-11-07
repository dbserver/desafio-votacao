package com.voting.resources;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.voting.entities.Topic;
import com.voting.services.TopicService;

@WebMvcTest(TopicResource.class)
public class TopicResourceTests {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private TopicService service;

	@Test
	void getTopics() throws Exception {
		this.mockMvc.perform(get("/v1/topics")).andExpect(status().isOk());
	}

	@Test
	public void saveTopic() throws Exception {
		Topic topic = new Topic();
		topic.setName("Topic test");

		String json = "{\n" + "  \"name\": \"Topic test\"\n" + "}";
		when(service.save(topic)).thenReturn(topic);
		mockMvc.perform(post("/v1/topics").contentType(MediaType.APPLICATION_JSON).content(json))
				.andExpect(status().isCreated());
	}

}
