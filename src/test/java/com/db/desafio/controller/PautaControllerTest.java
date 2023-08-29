package com.db.desafio.controller;


import com.db.desafio.exception.PautaException;
import com.db.desafio.service.PautaService;
import com.google.gson.Gson;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static com.db.desafio.util.factory.PautaFactory.ListaDePautasFactory;
import static com.db.desafio.util.factory.PautaFactory.pautaSemIdFactory;
import static com.db.desafio.util.factory.PautaResultadoDtoFactory.pautaResultadoDtoFactory;
import static com.db.desafio.util.provider.UrlProvider.*;
import static org.mockito.Mockito.*;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PautaController.class)
 class PautaControllerTest {

    @MockBean
    private PautaService pautaService;
    @Autowired
    private MockMvc mockMvc;


    @Test
    @DisplayName("Deve retornar status 201 quando uma nova pauta for criado")
    void deveRetornar201QuandoPautaCriadoComSucesso() throws Exception {
        String jsonBody = new Gson().toJson(pautaSemIdFactory());

        doNothing().when(pautaService).criarPauta(pautaSemIdFactory());

        mockMvc.perform(post(URI_PAUTA)
                .contentType(APPLICATION_JSON)
                        .content(jsonBody))
                .andExpect(status().isCreated());
        verify(pautaService,times(1)).criarPauta(pautaSemIdFactory());
    }

    @Test
    @DisplayName("Deve retornar status 404 quando tentar criar uma  pauta já existente")
    void deveRetornar404QuandoPautaExistir() throws Exception {
        String jsonBody = new Gson().toJson(pautaSemIdFactory());

        doThrow(PautaException.class).when(pautaService).criarPauta(pautaSemIdFactory());

        mockMvc.perform(post(URI_PAUTA)
                        .contentType(APPLICATION_JSON)
                        .content(jsonBody))
                .andExpect(status().isNotFound());
        verify(pautaService,times(1)).criarPauta(pautaSemIdFactory());
    }

    @Test
    @DisplayName("Deve retornar status 204 quando deletar pauta")
    void deveRetornar204QuandoPautaDeletadoComSucesso() throws Exception {

        doNothing().when(pautaService).deletarPauta(ID);

        mockMvc.perform(delete(URI_PAUTA_ID))
                .andExpect(status().isNoContent());
        verify(pautaService,times(1)).deletarPauta(ID);
    }

    @Test
    @DisplayName("Deve retornar status 404 quando tentar deletar uma pauta inexistente")
    void deveRetornar404QuandoPautaInexistente() throws Exception {
        doThrow(PautaException.class).when(pautaService).deletarPauta(ID);

        mockMvc.perform(delete(URI_PAUTA_ID))
                .andExpect(status().isNotFound());
        verify(pautaService,times(1)).deletarPauta(ID);
    }

    @Test
    @DisplayName("Deve retornar status 200 ao busca pauta por id")
    void deveRetornar200QuandoBuscarPautaComSucesso() throws Exception {
        String jsonBody = new Gson().toJson(pautaSemIdFactory());

        when(pautaService.obterPautaPorId(ID)).thenReturn(pautaSemIdFactory());

        mockMvc.perform(get(URI_PAUTA_ID)
                        .contentType(APPLICATION_JSON)
                        .content(jsonBody))
                .andExpect(status().isOk());
        verify(pautaService,times(1)).obterPautaPorId(ID);
    }

    @Test
    @DisplayName("Deve retornar status 404 quando Buscar uma pauta inexistente")
    void deveRetornar404QuandoPautaInexiste() throws Exception {

        when(pautaService.obterPautaPorId(ID)).thenThrow(PautaException.class);

        mockMvc.perform(get(URI_PAUTA_ID))
                .andExpect(status().isNotFound());
        verify(pautaService,times(1)).obterPautaPorId(ID);
    }


    @Test
    @DisplayName("Deve retornar status 200 ao busca uma lista de pautas")
    void deveRetornar200QuandoBuscarListaDePautasComSucesso() throws Exception {
        String jsonBody = new Gson().toJson(ListaDePautasFactory());

        when(pautaService.obterPautas()).thenReturn(ListaDePautasFactory());

        mockMvc.perform(get(URI_PAUTA)
                        .contentType(APPLICATION_JSON)
                        .content(jsonBody))
                .andExpect(status().isOk());
        verify(pautaService,times(1)).obterPautas();
    }
    @Test
    @DisplayName("Deve retornar status 200 ao busca resultado de uma pauta por id")
    void deveRetornar200QuandoBuscarResultadoDaPautaComSucesso() throws Exception {
        String jsonBody = new Gson().toJson(pautaResultadoDtoFactory());

        when(pautaService.obterResultadoPauta(ID)).thenReturn(pautaResultadoDtoFactory());

        mockMvc.perform(get(URI_RESULTADO)
                        .contentType(APPLICATION_JSON)
                        .content(jsonBody))
                .andExpect(status().isOk());
        verify(pautaService,times(1)).obterResultadoPauta(ID);
    }
}
