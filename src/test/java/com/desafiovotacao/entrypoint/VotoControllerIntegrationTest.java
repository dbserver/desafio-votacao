package com.desafiovotacao.entrypoint;

import com.desafiovotacao.domain.Associado;
import com.desafiovotacao.domain.Pauta;
import com.desafiovotacao.domain.SessaoPauta;
import com.desafiovotacao.dto.*;
import com.desafiovotacao.repository.AssociadoRepository;
import com.desafiovotacao.repository.PautaRepository;
import com.desafiovotacao.repository.SessaoPautaRepository;
import com.google.protobuf.Api;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Rollback
public class VotoControllerIntegrationTest {

    @LocalServerPort
    private int port;

    @Autowired
    private SessaoPautaRepository sessaoPautaRepository;

    @Autowired
    private AssociadoRepository associadoRepository;

    @Autowired
    private PautaRepository pautaRepository;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void execute_shouldCreateAVoto() throws Exception {
        VotoAssociadoDTO votoAssociadoDTO = generateVotoAssociado();

        HttpEntity<VotoAssociadoDTO> entity = new HttpEntity<>(votoAssociadoDTO);

        ResponseEntity<ApiResponse<VotoAssociadoDTO>> response = this.restTemplate.exchange("http://localhost:" + port + "/v1/voto", HttpMethod.POST, entity, new ParameterizedTypeReference<ApiResponse<VotoAssociadoDTO>>() {
        });

        boolean notNullCondition = response.getBody().getData().getId() != null;
        Assertions.assertTrue(notNullCondition);
    }

    private VotoAssociadoDTO generateVotoAssociado() {
        Pauta pautaSalva = this.pautaRepository.save(new Pauta(null, "Teste", 0L, 0L, true, new ArrayList<>()));
        SessaoPauta sessaoPautaSalva = this.sessaoPautaRepository.save(new SessaoPauta(null, LocalDateTime.now(), LocalDateTime.now().plusHours(1), 1, pautaSalva));
        Associado associadoSalvo = this.associadoRepository.save(new Associado(null, "Teste", generateRandomCpF()));

        VotoAssociadoDTO votoAssociadoDTO = new VotoAssociadoDTO();
        votoAssociadoDTO.setData(LocalDateTime.now());
        votoAssociadoDTO.setTipo(TipoVotoEnum.SIM);
        votoAssociadoDTO.setAssociadoId(associadoSalvo.getId());
        votoAssociadoDTO.setPautaId(pautaSalva.getId());
        votoAssociadoDTO.setSessaoId(sessaoPautaSalva.getId());

        return votoAssociadoDTO;
    }

    private String generateRandomCpF() {
       return String.valueOf((long)Math.floor(Math.random() * 9_000_000_000L) + 1_000_000_000L);
    }
}
