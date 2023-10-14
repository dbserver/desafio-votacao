package com.desafiovotacao.entrypoint;

import com.desafiovotacao.dto.ApiResponse;
import com.desafiovotacao.dto.AssociadoDTO;
import com.desafiovotacao.repository.AssociadoRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.Rollback;

import javax.transaction.Transactional;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Transactional
@Rollback(value = false)
public class AssociadoControllerIntegrationTest {

    private AssociadoDTO associadoDTO;

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private AssociadoRepository associadoRepository;

    @BeforeEach
    public void setup() {
        this.associadoDTO = new AssociadoDTO();
        associadoDTO.setNome("Teste");
        associadoDTO.setCpf("123456789");
    }

    @AfterEach
    public void tearDown() {
        associadoRepository.deleteByCpf(this.associadoDTO.getCpf());
    }

    @Test
    public void execute_shouldCreateAAssociado() throws Exception {
        HttpEntity<AssociadoDTO> entity = new HttpEntity<>(this.associadoDTO);

        ResponseEntity<ApiResponse<AssociadoDTO>> response = this.restTemplate.exchange("http://localhost:" + port + "/v1/associados", HttpMethod.POST, entity, new ParameterizedTypeReference<ApiResponse<AssociadoDTO>>() {
        });

        //Realizando duas verificaçoes, pois existe um validador de CPF randomico, como solicitado
        boolean notNullCondition = response.getBody().getData() != null && response.getBody().getData().getId() != null;
        boolean hasErrorCondition = response.getBody().getError() != null && response.getBody().getError().equals("CPF não é válido");
        Assertions.assertTrue(notNullCondition || hasErrorCondition);
    }
}
