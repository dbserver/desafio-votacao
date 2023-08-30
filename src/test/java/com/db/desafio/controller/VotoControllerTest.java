package com.db.desafio.controller;


import com.google.gson.Gson;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static com.db.desafio.util.factory.VotoFactory.votoDtoFactory;
import static com.db.desafio.util.provider.UrlProvider.ID;
import static com.db.desafio.util.provider.UrlProvider.URI_VOTO;
import static org.mockito.Mockito.doNothing;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(VotoController.class)
 class VotoControllerTest {

    @MockBean
    private VotoService votoService;
    @Autowired
    private MockMvc mockMvc;


    @Test
    @DisplayName("Deve retornar status 201 quando salvar um novo voto")
    void deveRetornar201QuandoSalvarVotoComSucesso() throws Exception {
        String jsonBody = new Gson().toJson(votoDtoFactory());

        doNothing().when(votoService).salvarVoto(ID,votoDtoFactory());

        mockMvc.perform(post(URI_VOTO)
                .contentType(APPLICATION_JSON)
                        .content(jsonBody))
                .andExpect(status().isCreated());

    }
}
