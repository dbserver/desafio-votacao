package com.example.desafiovotacao.controllers;


import com.example.desafiovotacao.controller.SessionController;
import com.example.desafiovotacao.dto.CreatedSessionDTO;
import com.example.desafiovotacao.dto.SessionReturnDTO;
import com.example.desafiovotacao.dto.StartSessionDTO;
import com.example.desafiovotacao.service.implementations.SessionServiceImpl;
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
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureTestDatabase
@WebMvcTest(SessionController.class)
public class SessionControllerTest {

    @Autowired
    private MockMvc mockMvc;
    private ObjectMapper objectMapper = new ObjectMapper();

    @MockBean
    private SessionServiceImpl sessionServiceImpl;

    @Mock
    private StartSessionDTO startSessionDTO;
    private CreatedSessionDTO createdSessionDTO;
    private SessionReturnDTO sessionReturnDTO;

    @BeforeEach
    void setup() {
        startSessionDTO = StartSessionDTO.builder()
                .duration(60)
                .rulingId(1)
                .build();
        createdSessionDTO = CreatedSessionDTO.builder()
                .sessionId(1)
                .creationDate(DateUtils.formatDate(new Date()))
                .duration(startSessionDTO.getDuration())
                .build();
        sessionReturnDTO = SessionReturnDTO.builder()
                .id(1)
                .rulingId(1)
                .rulingTitle("Title test")
                .duration(60)
                .creationDate(createdSessionDTO.getCreationDate())
                .build();
    }

    @Test
    void shouldCreateSession() throws Exception {
        when(sessionServiceImpl.create(any(StartSessionDTO.class))).thenReturn(createdSessionDTO);

        mockMvc.perform(
                post("/session/create")
                        .content(objectMapper.writeValueAsString(startSessionDTO))
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(objectMapper.writeValueAsString(createdSessionDTO)));
    }

    @Test
    void shouldFindSessionById() throws Exception {
        when(sessionServiceImpl.listById(1)).thenReturn(sessionReturnDTO);

        mockMvc.perform(get("/session/list/{sessionId}", 1))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(objectMapper.writeValueAsString(sessionReturnDTO)));
    }

    @Test
    void shouldListAllSections() throws Exception {
        when(sessionServiceImpl.listAll()).thenReturn(List.of(sessionReturnDTO));

        mockMvc.perform(get("/session/list"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(objectMapper.writeValueAsString(List.of(sessionReturnDTO))));
    }

}