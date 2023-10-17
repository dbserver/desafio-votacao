package com.challenge.controller;

import com.challenge.dto.VoteRequestDto;
import com.challenge.model.Stave;
import com.challenge.model.StaveSession;
import com.challenge.repository.StaveRepository;
import com.challenge.repository.StaveSessionRepository;
import com.challenge.repository.VoteRepository;
import com.challenge.stubs.StaveSessionStub;
import com.challenge.stubs.StaveStub;
import com.challenge.stubs.VoteRequestDtoStub;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Map;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class VoteControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private VoteRepository voteRepository;
    @Autowired
    private StaveRepository staveRepository;
    @Autowired
    private StaveSessionRepository staveSessionRepository;
    @Autowired
    private ObjectMapper mapper;

    final static Long ASSOCIATE_ID = 1L;

    @Test
    @Order(1)
    void saveAssociateVote_whenSuccessful() throws Exception {
        Stave stave = staveRepository.save(StaveStub.build());
        StaveSession session = staveSessionRepository.save(StaveSessionStub.build(null, stave));

        Map<String, Object> metadata = Map.of("associateId", ASSOCIATE_ID, "staveSessionId", session.getId());
        VoteRequestDto requestDto = VoteRequestDtoStub.build(metadata);

        mockMvc.perform(MockMvcRequestBuilders.post("/v1/vote")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(requestDto)))
                .andExpect(MockMvcResultMatchers.status().isCreated());
    }

    @Test
    @Order(2)
    void saveAssociateVote_whenFailureBadRequest() throws Exception {
        Map<String, Object> metadata = Map.of("associateId", ASSOCIATE_ID, "staveSessionId", 1L);
        VoteRequestDto request = VoteRequestDtoStub.build(metadata);

        mockMvc.perform(MockMvcRequestBuilders.post("/v1/vote")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(request)))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    @Order(3)
    void saveAssociateVote_whenFailureBadRequestClosedSession() throws Exception {
        staveSessionRepository.save(StaveSessionStub.buildClose(1L));
        mockMvc.perform(MockMvcRequestBuilders.post("/v1/vote")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(VoteRequestDtoStub.build())))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    @Order(4)
    void saveAssociateVote_whenFailureNotFound() throws Exception {
        String request = mapper.writeValueAsString(VoteRequestDtoStub.build(Map.of("associateId", 4L, "staveSessionId", 10L)));
        mockMvc.perform(MockMvcRequestBuilders.post("/v1/vote")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(request))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }
}
