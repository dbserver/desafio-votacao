package com.voting.resources;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.voting.services.SessionService;
import com.voting.services.TopicService;

@WebMvcTest(SessionResource.class)
public class SessionResourceTests {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private SessionService service;

	@MockBean
	private TopicService topicService;

	@Test
	void getSessions() throws Exception {
		this.mockMvc.perform(get("/v1/sessions").param("showClosedSessions", "N")).andExpect(status().isOk());
	}

	@Test
	public void startVotingSession() throws Exception {
		mockMvc.perform(post("/v1/sessions/1/start-voting-session").param("minutesVoting", "5")
				.contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
	}

}
