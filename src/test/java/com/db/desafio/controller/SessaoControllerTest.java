package com.db.desafio.controller;


import com.db.desafio.exception.PautaException;
import com.db.desafio.service.PautaService;
import com.db.desafio.service.SessaoService;
import com.google.gson.Gson;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static com.db.desafio.util.factory.PautaFactory.ListaDePautasFactory;
import static com.db.desafio.util.factory.PautaFactory.pautaSemIdFactory;
import static com.db.desafio.util.factory.SessaoFactory.sessaoFactory;
import static com.db.desafio.util.provider.UrlProvider.*;
import static org.mockito.Mockito.*;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
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






}
