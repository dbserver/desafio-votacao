package com.desafiovotacao.entrypoint;

import com.desafiovotacao.dto.PautaDTO;
import com.desafiovotacao.repository.PautaRepository;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PautaControllerIntegrationTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private PautaRepository pautaRepository;


    @Test
    public void execute_shouldCreateAPauta() throws Exception {
        HttpEntity<PautaDTO> entity = new HttpEntity<>(generatePautaDTO());

        ResponseEntity<PautaDTO> response = this.restTemplate.exchange("http://localhost:" + port + "/v1/pautas", HttpMethod.POST, entity, new ParameterizedTypeReference<PautaDTO>() {
        });

        boolean notNullCondition = response.getBody().getId() != null;
        Assertions.assertTrue(notNullCondition);
    }

    private PautaDTO generatePautaDTO() {
        PautaDTO pautaDTO = new PautaDTO();
        pautaDTO.setVotosAFavor(0L);
        pautaDTO.setVotosContra(0L);
        pautaDTO.setDescricao("Teste");
        return pautaDTO;
    }
}
