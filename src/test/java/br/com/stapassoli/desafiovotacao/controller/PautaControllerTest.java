package br.com.stapassoli.desafiovotacao.controller;

import br.com.stapassoli.desafiovotacao.dto.PautaDTO;
import br.com.stapassoli.desafiovotacao.entity.Pauta;
import br.com.stapassoli.desafiovotacao.repository.PautaRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
class PautaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    void deveCadastrarPauta() throws Exception {

        PautaDTO pauta = PautaDTO
            .builder()
            .assunto("Red Dead Redemption 2 melhor game de todos os tempos ?")
            .build();

        var pautaJson = objectMapper.writeValueAsString(pauta);

        String URI = "/pauta";

        var jsonResp = mockMvc
            .perform(MockMvcRequestBuilders.post(URI)
                .contentType(MediaType.APPLICATION_JSON)
                .content(pautaJson))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andReturn().getResponse().getContentAsString();

        Pauta pautaCadastrada = objectMapper.readValue(jsonResp, Pauta.class);

        assert pautaCadastrada.getAssunto().equals(pauta.getAssunto());
    }

}