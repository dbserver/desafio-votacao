package br.com.stapassoli.desafiovotacao.controller;

import br.com.stapassoli.desafiovotacao.dto.PautaDTO;
import br.com.stapassoli.desafiovotacao.dto.SessaoDTO;
import br.com.stapassoli.desafiovotacao.entity.Sessao;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Objects;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
@TestInstance(Lifecycle.PER_CLASS)
class SessaoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @BeforeAll
    public void criarPauta () throws Exception {
        PautaDTO pauta = PautaDTO
            .builder()
            .assunto("Red Dead Redemption 2 melhor game de todos os tempos ?")
            .build();

        var pautaJson = objectMapper.writeValueAsString(pauta);

        String URI = "/pauta";

        mockMvc
            .perform(MockMvcRequestBuilders.post(URI)
                .contentType(MediaType.APPLICATION_JSON)
                .content(pautaJson))
            .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void deveCadastrarSessao() throws Exception {

        SessaoDTO sessaoDTO = SessaoDTO
            .builder()
            .idPauta(1L)
            .build();

        var sessaoJson = objectMapper.writeValueAsString(sessaoDTO);

        String URI = "/sessao";

        String jsonResponse = mockMvc
            .perform(MockMvcRequestBuilders.post(URI)
                .contentType(MediaType.APPLICATION_JSON)
                .content(sessaoJson))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andReturn().getResponse().getContentAsString();

        Sessao sessao = objectMapper.readValue(jsonResponse, Sessao.class);

        assert Objects.nonNull(sessao);

    }

}