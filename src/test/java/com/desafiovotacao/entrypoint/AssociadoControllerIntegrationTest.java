package com.desafiovotacao.entrypoint;

import com.desafiovotacao.dto.ApiResponse;
import com.desafiovotacao.dto.AssociadoDTO;
import com.desafiovotacao.repository.AssociadoRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AssociadoControllerIntegrationTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private AssociadoRepository associadoRepository;

    @Test
    public void execute_shouldCreateAAssociado() throws Exception {
        AssociadoDTO associadoDTO = generateAssociadoDTO();

        HttpEntity<AssociadoDTO> entity = new HttpEntity<>(associadoDTO);

        ResponseEntity<ApiResponse<AssociadoDTO>> response = this.restTemplate.exchange("http://localhost:" + port + "/v1/associados", HttpMethod.POST, entity, new ParameterizedTypeReference<ApiResponse<AssociadoDTO>>() {
        });

        //Realizando duas verificaçoes, pois existe um validador de CPF randomico, como solicitado
        boolean notNullCondition = response.getBody().getData() != null && response.getBody().getData().getId() != null;
        boolean hasErrorCondition = response.getBody().getError() != null && response.getBody().getError().equals("CPF não é válido");
        Assertions.assertTrue(notNullCondition || hasErrorCondition);
    }

    private AssociadoDTO generateAssociadoDTO() {
        AssociadoDTO associadoDTO = new AssociadoDTO();
        associadoDTO.setNome("Teste");
        associadoDTO.setCpf(generateRandomCpF());
        return associadoDTO;
    }

    private String generateRandomCpF() {
        return String.valueOf((long)Math.floor(Math.random() * 9_000_000_000L) + 1_000_000_000L);
    }
}
