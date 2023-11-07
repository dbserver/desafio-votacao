package com.voting.resources;

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
import com.voting.services.VoteService;

@WebMvcTest(VoteResource.class)
public class VoteResourceTests {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private VoteService service;

	@MockBean
	private SessionService sessionService;

	@MockBean
	private TopicService topicService;

	@Test
	public void registerVote() throws Exception {
		String json = "{\n" + "  \"cpfVoter\": \"12345678912\",\n" + "  \"voteMessage\": \"SIM\"\n" + "}";

		mockMvc.perform(post("/v1/votes/1/vote").contentType(MediaType.APPLICATION_JSON).content(json))
				.andExpect(status().isOk());
	}

}
