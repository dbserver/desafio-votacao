package com.example.desafiovotacao.controllers;


import com.example.desafiovotacao.controller.FacadeController;
import com.example.desafiovotacao.dto.FacadeDTO;
import com.example.desafiovotacao.facade.CpfFacade;
import com.example.desafiovotacao.utils.CpfUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureTestDatabase
@WebMvcTest(FacadeController.class)
public class FacadeControllerTest {

    @Autowired
    private MockMvc mockMvc;
    private ObjectMapper objectMapper = new ObjectMapper();

    @MockBean
    private CpfFacade cpfFacade;

    @Mock
    private FacadeDTO validFacadeDTO;
    private FacadeDTO invalidFacadeDTO;
    private String cpf;

    @BeforeEach
    void setup() {
        validFacadeDTO = FacadeDTO.builder()
                .status("ABLE_TO_VOTE")
                .build();
        invalidFacadeDTO = FacadeDTO.builder()
                .status("UNABLE_TO_VOTE")
                .build();
        cpf = CpfUtils.generateCPF();
    }

    @Test
    void shouldCpfBeValid() throws Exception {
        when(cpfFacade.isCpfValid(cpf)).thenReturn(ResponseEntity.status(HttpStatus.OK).body(validFacadeDTO));

        mockMvc.perform(post("/facade/{cpf}/validate", cpf))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(objectMapper.writeValueAsString(validFacadeDTO)));
    }

    @Test
    void shouldCpfBeInvalid() throws Exception {
        when(cpfFacade.isCpfValid(cpf)).thenReturn(ResponseEntity.status(HttpStatus.NOT_FOUND).body(invalidFacadeDTO));

        mockMvc.perform(post("/facade/{cpf}/validate", cpf))
                .andExpect(status().isNotFound())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(objectMapper.writeValueAsString(invalidFacadeDTO)));
    }
}
