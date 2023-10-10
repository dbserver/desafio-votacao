package com.challenge.controller;

import com.challenge.repository.AssociateRepository;
import com.challenge.repository.StaveSessionRepository;
import com.challenge.repository.VoteRepository;
import com.challenge.service.VoteService;
import com.challenge.stubs.StaveSessionStub;
import com.challenge.stubs.VoteRequestDtoStub;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringJUnitConfig
@AutoConfigureMockMvc
@WebMvcTest(VoteController.class)
@ContextConfiguration(classes = {VoteController.class})
public class VoteControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private VoteService voteService;
    @MockBean
    private VoteRepository voteRepository;
    @MockBean
    private StaveSessionRepository staveSessionRepository;
    @MockBean
    private AssociateRepository associateRepository;
    @Autowired
    private ObjectMapper mapper;

    @Test
    void saveAssociateVote_whenSuccessful() throws Exception {

        when(voteRepository.existsByAssociateAndSession(any(), any())).thenReturn(false);
        when(staveSessionRepository.getReferenceById(any())).thenReturn(StaveSessionStub.build());

        mockMvc.perform(MockMvcRequestBuilders.post("/v1/vote")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(VoteRequestDtoStub.build())))
                .andExpect(MockMvcResultMatchers.status().isCreated());
    }
}
