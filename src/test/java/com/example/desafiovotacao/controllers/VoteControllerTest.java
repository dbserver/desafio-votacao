package com.example.desafiovotacao.controllers;

import com.example.desafiovotacao.controller.VoteController;
import com.example.desafiovotacao.dto.ComputingVoteDTO;
import com.example.desafiovotacao.dto.VotedDTO;
import com.example.desafiovotacao.service.implementations.VoteServiceImpl;
import com.example.desafiovotacao.utils.CpfUtils;
import com.example.desafiovotacao.utils.DateUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Date;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureTestDatabase
@WebMvcTest(VoteController.class)
public class VoteControllerTest {

    @Autowired
    private MockMvc mockMvc;

    private ObjectMapper objectMapper = new ObjectMapper();

    @MockBean
    private VoteServiceImpl voteService;

    @Mock
    private ComputingVoteDTO computingVoteDTO;
    private VotedDTO votedDTO;

    @BeforeEach
    void setup() {
        computingVoteDTO = ComputingVoteDTO.builder()
                .vote(true)
                .cpf(CpfUtils.generateCPF())
                .sessionId(1)
                .build();
        votedDTO = VotedDTO.builder()
                .voteId(1)
                .sessionId(1)
                .computedVote("Sim")
                .cpfAssociate(computingVoteDTO.getCpf())
                .topic("Title test")
                .sessionDate(DateUtils.formatDate(new Date()))
                .build();
    }

    @Test
    void shouldComputeVote() throws Exception {
        when(voteService.create(computingVoteDTO)).thenReturn(votedDTO);

        mockMvc.perform(
                post("/vote/compute")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(computingVoteDTO))
        ).andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(objectMapper.writeValueAsString(votedDTO)));
    }

}
