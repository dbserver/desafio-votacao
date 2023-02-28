package br.tec.db.desafio.api.v1.controller;

import br.tec.db.desafio.api.v1.dto.associado.AssociadoRequestV1;
import br.tec.db.desafio.api.v1.dto.associado.AssociadoResponseV1;
import br.tec.db.desafio.api.v1.dto.pauta.PautaRequestV1;
import br.tec.db.desafio.api.v1.dto.pauta.PautaResponseV1;
import br.tec.db.desafio.business.service.IAssociadoService;
import br.tec.db.desafio.business.service.IPautaService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.server.LocalServerPort;

import static io.restassured.RestAssured.given;

@ExtendWith(MockitoExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AssociadoControllerV1Test {
    @MockBean
    IAssociadoService associadoService;
    private static final String NOME = "guilherme";
    private static final String CPF = "12312366990";
    private static final String URI ="/api/v1/associado";
    @LocalServerPort
    private int port;

    @BeforeEach
    void setup() {
        RestAssured.port = this.port;
    }

    @Test
    void devePersistirPautaComSucesso() throws JsonProcessingException {
        AssociadoRequestV1 associadoRequestV1 =
                new AssociadoRequestV1(
                        NOME,
                        CPF);

        AssociadoResponseV1 associadoResponseV1 =
                new AssociadoResponseV1(
                        NOME,
                        CPF);
        String request = new ObjectMapper().writeValueAsString(associadoRequestV1);

        Mockito.when(associadoService.criarUmNovoAssociado(associadoRequestV1))
                .thenReturn(associadoResponseV1);

        given()
                .when()
                .contentType(ContentType.JSON)
                .body(request)
                .post(URI)
                .then()
                .statusCode(201);


    }

}

