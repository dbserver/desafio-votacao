package com.desafio.projeto_votacao.controller;

import com.desafio.projeto_votacao.dto.AssociadoRequestDto;
import com.desafio.projeto_votacao.repository.AssociadoRepository;
import com.desafio.projeto_votacao.service.AssociadoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
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

import java.util.Collections;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringJUnitConfig
@AutoConfigureMockMvc
@WebMvcTest(AssociadoController.class)
@ContextConfiguration(classes = {AssociadoController.class})
class AssociadoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AssociadoService associadoService;

    @MockBean
    private AssociadoRepository associadoRepository;

    @BeforeEach
    void setUp() {
        Mockito.when(associadoService.listarAssociados()).thenReturn(Collections.emptyList());
    }

    @Test
    void listarAssociados_DeveRetornarListaVazia() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/public/v1/associados/listar"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(0));
    }

    @Test
    void cadastrarAssociado_DeveRetornarSucesso() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.post("/api/public/v1/associados/cadastrar")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{ \"nome\": \"Nome\", \"cpf\": \"12345678909\" }"))
                .andExpect(status().isCreated())
                .andExpect(MockMvcResultMatchers.content().string("Associado cadastrado com sucesso."));
    }
}
