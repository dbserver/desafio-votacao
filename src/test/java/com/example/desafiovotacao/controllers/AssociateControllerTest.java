package com.example.desafiovotacao.controllers;

import com.example.desafiovotacao.controller.AssociateController;
import com.example.desafiovotacao.dto.CreatedAssociateDTO;
import com.example.desafiovotacao.dto.RegisterAssociateDTO;
import com.example.desafiovotacao.service.implementations.AssociateServiceImpl;
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
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureTestDatabase
@WebMvcTest(AssociateController.class)
public class AssociateControllerTest {

    @Autowired
    private MockMvc mockMvc;
    private ObjectMapper objectMapper = new ObjectMapper();

    @MockBean
    private AssociateServiceImpl associateServiceImpl;

    @Mock
    private RegisterAssociateDTO registerAssociateDTO;
    private CreatedAssociateDTO createdAssociateDTO;

    @BeforeEach
    void setup() {
        registerAssociateDTO = RegisterAssociateDTO.builder()
                .cpf(CpfUtils.generateCPF())
                .name("User test")
                .build();
        createdAssociateDTO = CreatedAssociateDTO.builder()
                .id(1)
                .cpf(registerAssociateDTO.getCpf())
                .build();
    }

    @Test
    void testCreateAssociate() throws Exception{
        when(associateServiceImpl.create(registerAssociateDTO)).thenReturn(createdAssociateDTO);

        mockMvc.perform(
                post("/associate/create")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(objectMapper.writeValueAsString(registerAssociateDTO))
                )
                .andExpect(status().isCreated());
    }

}
