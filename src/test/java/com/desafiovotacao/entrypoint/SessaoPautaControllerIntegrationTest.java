package com.desafiovotacao.entrypoint;

import com.desafiovotacao.domain.Pauta;
import com.desafiovotacao.dto.SessaoPautaDTO;
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

import java.time.LocalDateTime;
import java.util.ArrayList;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SessaoPautaControllerIntegrationTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private PautaRepository pautaRepository;

    @Test
    public void execute_shouldCreateASessaoPauta() throws Exception {
        HttpEntity<SessaoPautaDTO> entity = new HttpEntity<>(generateSessaoPauta());

        ResponseEntity<SessaoPautaDTO> response = this.restTemplate.exchange("http://localhost:" + port + "/v1/sessao-pauta", HttpMethod.POST, entity, new ParameterizedTypeReference<SessaoPautaDTO>() {
        });

        boolean notNullCondition = response.getBody().getId() != null;
        Assertions.assertTrue(notNullCondition);
    }

    private SessaoPautaDTO generateSessaoPauta() {
        Pauta pautaSalva = this.pautaRepository.save(new Pauta(null, "Teste", 0L, 0L, true, new ArrayList<>()));

        SessaoPautaDTO sessaoPautaDTO = new SessaoPautaDTO();
        sessaoPautaDTO.setPautaId(pautaSalva.getId());
        sessaoPautaDTO.setDuracaoMinutos(2);
        sessaoPautaDTO.setDataInicio(LocalDateTime.now());
        sessaoPautaDTO.setDataFim(LocalDateTime.now());
        return sessaoPautaDTO;
    }

}
