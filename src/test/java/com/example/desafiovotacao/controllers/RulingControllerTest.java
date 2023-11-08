package com.example.desafiovotacao.controllers;

import com.example.desafiovotacao.controller.RulingController;
import com.example.desafiovotacao.dto.CountingResultsDTO;
import com.example.desafiovotacao.dto.CreatedRulingDTO;
import com.example.desafiovotacao.dto.RegisterRulingDTO;
import com.example.desafiovotacao.dto.RulingReturnDTO;
import com.example.desafiovotacao.service.implementations.RulingServiceImpl;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureTestDatabase
@WebMvcTest(RulingController.class)
public class RulingControllerTest {
    @Autowired
    private MockMvc mockMvc;
    private ObjectMapper objectMapper = new ObjectMapper();

    @MockBean
    private RulingServiceImpl rulingServiceImpl;

    @Mock
    private RegisterRulingDTO registerRulingDTO;
    private CreatedRulingDTO createdRulingDTO;
    private RulingReturnDTO rulingReturnDTO;
    private CountingResultsDTO countingResultsDTO;

    @BeforeEach
    void setup() {
        registerRulingDTO = RegisterRulingDTO.builder()
                .description("Description test")
                .title("Title test")
                .build();
        createdRulingDTO = CreatedRulingDTO.builder()
                .rulingId(1)
                .description(registerRulingDTO.getDescription())
                .title(registerRulingDTO.getTitle())
                .build();
        rulingReturnDTO = RulingReturnDTO.builder()
                .id(1)
                .resultDate(null)
                .creationDate(DateUtils.formatDate(new Date()))
                .title(createdRulingDTO.getTitle())
                .description(createdRulingDTO.getDescription())
                .result(null)
                .build();
        countingResultsDTO = CountingResultsDTO.builder()
                .result("Sim")
                .againstVotes(0L)
                .inFavorVotes(1L)
                .countDate(DateUtils.formatDate(new Date()))
                .creationDate(DateUtils.formatDate(new Date()))
                .sessionDate(DateUtils.formatDate(new Date()))
                .title(createdRulingDTO.getTitle())
                .description(createdRulingDTO.getDescription())
                .build();
    }


    @Test
    void shouldCountVotes() throws Exception {
        when(rulingServiceImpl.countVotes(1)).thenReturn(countingResultsDTO);

        mockMvc.perform(post("/ruling/countVotes/{rulingId}", 1))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(objectMapper.writeValueAsString(countingResultsDTO)));
    }

    @Test
    void shouldCreateRuling() throws Exception {
        when(rulingServiceImpl.create(any(RegisterRulingDTO.class))).thenReturn(createdRulingDTO);

        mockMvc.perform(post("/ruling/create")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(registerRulingDTO))
                )
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(objectMapper.writeValueAsString(createdRulingDTO)));
    }

    @Test
    void shouldListAllRuling() throws Exception {
        when(rulingServiceImpl.listAll()).thenReturn(List.of(rulingReturnDTO));

        mockMvc.perform(get("/ruling/list"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(objectMapper.writeValueAsString(List.of(rulingReturnDTO))));
    }

    @Test
    void shouldReturnRulingById() throws Exception {
        when(rulingServiceImpl.getRulingReturnIfExists(1)).thenReturn(rulingReturnDTO);

        mockMvc.perform( get("/ruling/list/{id}", 1))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(objectMapper.writeValueAsString(rulingReturnDTO)))
        ;
    }
}
