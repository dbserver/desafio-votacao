package com.db.desafio.controller;


import com.db.desafio.exception.AssociadoException;
import com.db.desafio.service.AssociadoService;
import com.google.gson.Gson;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static com.db.desafio.util.factory.AssociadoFactory.*;
import static com.db.desafio.util.provider.UrlProvider.*;
import static org.mockito.Mockito.*;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AssociadoController.class)
 class AssociadoControllerTest {

    @MockBean
    private AssociadoService associadoService;
    @Autowired
    private MockMvc mockMvc;


    @Test
    @DisplayName("Deve retornar status 201 quando um novo associado for criado")
    void deveRetornar201QuandoAssociadoCriadoComSucesso() throws Exception {
        String jsonBody = new Gson().toJson(associadoDtoFactory());

        doNothing().when(associadoService).criarAssociado(associadoSemIdFactory());

        mockMvc.perform(post(URI_ASSOCIADO)
                .contentType(APPLICATION_JSON)
                        .content(jsonBody))
                .andExpect(status().isCreated());
        verify(associadoService,times(1)).criarAssociado(associadoSemIdFactory());
    }

    @Test
    @DisplayName("Deve retornar status 404 quando criar um  associado já existente")
    void deveRetornar404QuandoAssociadoExistir() throws Exception {
        String jsonBody = new Gson().toJson(associadoDtoFactory());

        doThrow(AssociadoException.class).when(associadoService).criarAssociado(associadoSemIdFactory());

        mockMvc.perform(post(URI_ASSOCIADO)
                        .contentType(APPLICATION_JSON)
                        .content(jsonBody))
                .andExpect(status().isNotFound());
        verify(associadoService,times(1)).criarAssociado(associadoSemIdFactory());
    }

    @Test
    @DisplayName("Deve retornar status 204 quando deletar associado")
    void deveRetornar204QuandoAssociadoDeletadoComSucesso() throws Exception {

        doNothing().when(associadoService).deletarAssociado(ID);

        mockMvc.perform(delete(URI_ASSOCIADO_ID))
                .andExpect(status().isNoContent());
        verify(associadoService,times(1)).deletarAssociado(ID);
    }

    @Test
    @DisplayName("Deve retornar status 404 quando tentar deletar um associado inexistente")
    void deveRetornar404QuandoAssociadoInexistente() throws Exception {
        doThrow(AssociadoException.class).when(associadoService).deletarAssociado(ID);

        mockMvc.perform(delete(URI_ASSOCIADO_ID))
                .andExpect(status().isNotFound());
        verify(associadoService,times(1)).deletarAssociado(ID);
    }

    @Test
    @DisplayName("Deve retornar status 200 ao busca associado por id")
    void deveRetornar200QuandoBuscarAssociadoComSucesso() throws Exception {
        String jsonBody = new Gson().toJson(associadoDtoFactory());

        when(associadoService.obterAssociadoPorId(ID)).thenReturn(associadoFactory());

        mockMvc.perform(get(URI_ASSOCIADO_ID)
                        .contentType(APPLICATION_JSON)
                        .content(jsonBody))
                .andExpect(status().isOk());
        verify(associadoService,times(1)).obterAssociadoPorId(ID);
    }

    @Test
    @DisplayName("Deve retornar status 404 quando Buscar um  associado inexistente")
    void deveRetornar404QuandoAssociadoInexiste() throws Exception {

        when(associadoService.obterAssociadoPorId(ID)).thenThrow(AssociadoException.class);

        mockMvc.perform(get(URI_ASSOCIADO_ID))
                .andExpect(status().isNotFound());
        verify(associadoService,times(1)).obterAssociadoPorId(ID);
    }


    @Test
    @DisplayName("Deve retornar status 200 ao busca uma lista de associados")
    void deveRetornar200QuandoBuscarListaDeAssociadoComSucesso() throws Exception {
        String jsonBody = new Gson().toJson(ListaDeAssociadosDtoFactory());

        when(associadoService.obterAssociados()).thenReturn(ListaDeAssociadosFactory());

        mockMvc.perform(get(URI_ASSOCIADO)
                        .contentType(APPLICATION_JSON)
                        .content(jsonBody))
                .andExpect(status().isOk());
        verify(associadoService,times(1)).obterAssociados();
    }
}
