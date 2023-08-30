package com.db.desafio.controller;


import com.db.desafio.exception.SessaoVotacaoException;
import com.db.desafio.service.SessaoVotacaoService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static com.db.desafio.util.factory.SessaoFactory.ListaDeSessoesFactory;
import static com.db.desafio.util.factory.SessaoFactory.sessaoSemIdFactory;
import static com.db.desafio.util.provider.UrlProvider.*;
import static org.mockito.Mockito.*;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(SessaoVotacaoController.class)
 class SessaoVotacaoControllerTest {

    @MockBean
    private SessaoVotacaoService sessaoVotacaoService;
    @Autowired
    private MockMvc mockMvc;


    @Test
    @DisplayName("Deve retornar status 201 quando uma nova sessao for aberta")
    void deveRetornar201QuandoSessaoForAbertaComSucesso() throws Exception {
        doNothing().when(sessaoVotacaoService).abrirSessao(ID);
        mockMvc.perform(post(URI_SESSAO)
                        .param("pautaId","1"))
                .andExpect(status().isCreated());
        verify(sessaoVotacaoService, times(1)).abrirSessao(ID);
    }
    @Test
    @DisplayName("Deve retornar status 200 ao busca uma lista de sessoes")
    void deveRetornar200QuandoBuscarListaDesessoesComSucesso() throws Exception {
        when(sessaoVotacaoService.obterSessoes()).thenReturn(ListaDeSessoesFactory());
        mockMvc.perform(get(URI_SESSAO)
                        .contentType(APPLICATION_JSON))
                .andExpect(status().isOk());
        verify(sessaoVotacaoService,times(1)).obterSessoes();
    }
    @Test
    @DisplayName("Deve retornar status 200 ao busca sessao por id")
    void deveRetornar200QuandoBuscarSessaoComSucesso() throws Exception {

        when(sessaoVotacaoService.obterSessao(ID)).thenReturn(sessaoSemIdFactory());

        mockMvc.perform(get(URI_SESSAO_ID)
                        .contentType(APPLICATION_JSON))
                .andExpect(status().isOk());
        verify(sessaoVotacaoService,times(1)).obterSessao(ID);
    }

    @Test
    @DisplayName("Deve retornar status 404 quando Buscar um sessao inexistente")
    void deveRetornar404QuandoSessaoInexiste() throws Exception {

        when(sessaoVotacaoService.obterSessao(ID)).thenThrow(SessaoVotacaoException.class);

        mockMvc.perform(get(URI_SESSAO_ID))
                .andExpect(status().isNotFound());
        verify(sessaoVotacaoService,times(1)).obterSessao(ID);
    }







}
