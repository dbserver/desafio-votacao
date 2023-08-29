package com.db.desafio.controller;


import com.db.desafio.exception.AssociadoException;
import com.db.desafio.exception.SessaoException;
import com.db.desafio.service.SessaoService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;

import static com.db.desafio.util.factory.AssociadoFactory.associadoSemIdFactory;
import static com.db.desafio.util.factory.SessaoFactory.*;
import static com.db.desafio.util.provider.UrlProvider.*;
import static com.db.desafio.util.provider.UrlProvider.URI_ASSOCIADO_ID;
import static org.mockito.Mockito.*;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(SessaoController.class)
 class SessaoControllerTest {

    @MockBean
    private SessaoService sessaoService;
    @Autowired
    private MockMvc mockMvc;


    @Test
    @DisplayName("Deve retornar status 201 quando uma nova sessao for aberta")
    void deveRetornar201QuandoSessaoForAbertaComSucesso() throws Exception {
        doNothing().when(sessaoService).abrirSessao(ID);
        mockMvc.perform(post(URI_SESSAO)
                        .param("pautaId","1"))
                .andExpect(status().isCreated());
        verify(sessaoService, times(1)).abrirSessao(ID);
    }
    @Test
    @DisplayName("Deve retornar status 200 ao busca uma lista de sessoes")
    void deveRetornar200QuandoBuscarListaDesessoesComSucesso() throws Exception {
        when(sessaoService.obterSessoes()).thenReturn(ListaDeSessoesFactory());

        mockMvc.perform(get(URI_SESSAO)
                        .contentType(APPLICATION_JSON))
                .andExpect(status().isOk());
        verify(sessaoService,times(1)).obterSessoes();
    }
    @Test
    @DisplayName("Deve retornar status 200 ao busca sessao por id")
    void deveRetornar200QuandoBuscarSessaoComSucesso() throws Exception {

        when(sessaoService.obterSessao(ID)).thenReturn(sessaoSemIdFactory());

        mockMvc.perform(get(URI_SESSAO_ID)
                        .contentType(APPLICATION_JSON))
                .andExpect(status().isOk());
        verify(sessaoService,times(1)).obterSessao(ID);
    }

    @Test
    @DisplayName("Deve retornar status 404 quando Buscar um sessao inexistente")
    void deveRetornar404QuandoSessaoInexiste() throws Exception {

        when(sessaoService.obterSessao(ID)).thenThrow(SessaoException.class);

        mockMvc.perform(get(URI_SESSAO_ID))
                .andExpect(status().isNotFound());
        verify(sessaoService,times(1)).obterSessao(ID);
    }







}
