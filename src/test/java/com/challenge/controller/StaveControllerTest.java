package com.challenge.controller;

import com.challenge.model.StaveSession;
import com.challenge.model.Vote;
import com.challenge.repository.StaveRepository;
import com.challenge.repository.StaveSessionRepository;
import com.challenge.repository.VoteRepository;
import com.challenge.stubs.StaveSessionStub;
import com.challenge.stubs.StaveStub;
import com.challenge.stubs.VoteStub;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;


@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class StaveControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private StaveRepository staveRepository;

    @Autowired
    private StaveSessionRepository sessionRepository;

    @Autowired
    private VoteRepository voteRepository;

    @Test
    @Order(1)
    void createStave_whenSuccessful() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/v1/stave")
                        .contentType("application/json")
                        .content("{\"title\":\"Teste\"}"))
                .andExpect(MockMvcResultMatchers.status().isCreated());
    }

    @Test
    @Order(2)
    void createStave_whenFailureBadRequest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/v1/stave")
                        .contentType("application/json")
                        .content("{\"title\":\"\"}"))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());

        mockMvc.perform(MockMvcRequestBuilders.post("/v1/stave")
                        .contentType("application/json")
                        .content("{\"title\":null}"))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    @Order(3)
    void createStaveSession_whenSuccessful() throws Exception {
        final Long staveId = 1L;
        mockMvc.perform(MockMvcRequestBuilders.post("/v1/stave/{staveId}/session", staveId)
                        .contentType("application/json")
                        .content("{\"duration\":10}"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.duration").value("10"));

        staveRepository.save(StaveStub.build(2L));

        mockMvc.perform(MockMvcRequestBuilders.post("/v1/stave/{staveId}/session", 2L)
                        .contentType("application/json")
                        .content("{\"duration\":null}"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.duration").value("1"));
    }

    @Test
    @Order(4)
    void createStaveSession_whenFailureNotFound() throws Exception {
        final Long staveId = 3L;
        mockMvc.perform(MockMvcRequestBuilders.post("/v1/stave/{staveId}/session", staveId)
                        .contentType("application/json")
                        .content("{\"duration\":10}"))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    @Order(5)
    void countVotes_whenFailureBadRequestSessionOpen() throws Exception {
        final Long staveId = 1L;
        mockMvc.perform(MockMvcRequestBuilders.post("/v1/stave/{staveId}/count-votes", staveId)
                        .contentType("application/json"))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    @Order(6)
    void countVotes_whenSuccessful() throws Exception {
        final Long staveId = 1L;

        //force close
        StaveSession session =  sessionRepository.save(StaveSessionStub.buildClose(staveId));
        voteRepository.save(VoteStub.build(session));

        mockMvc.perform(MockMvcRequestBuilders.post("/v1/stave/{staveId}/count-votes", staveId)
                        .contentType("application/json"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.total").value("1"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.yesVotes").value("1"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.noVotes").value("0"))
                .andDo(MockMvcResultHandlers.print());
    }
}
