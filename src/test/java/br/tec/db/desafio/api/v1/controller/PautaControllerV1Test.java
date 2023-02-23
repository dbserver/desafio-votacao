package br.tec.db.desafio.api.v1.controller;

import br.tec.db.desafio.api.v1.dto.pauta.PautaRequestV1;
import br.tec.db.desafio.api.v1.dto.pauta.PautaResponseV1;
import br.tec.db.desafio.business.service.PautaService;
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
public class PautaControllerV1Test {
    @MockBean
    PautaService pautaService;

    @LocalServerPort
    private int port;

    @BeforeEach
    void setup() {
        RestAssured.port = this.port;
    }

    @Test
    void devePersistirPautaComSucesso() throws JsonProcessingException {
        PautaRequestV1 pautaRequestV1 =
                new PautaRequestV1(
                        "tema da pauta");

        PautaResponseV1 pautaResponseV1 =
                new PautaResponseV1(
                        "tema da pauta"
                );
        String request = new ObjectMapper().writeValueAsString(pautaRequestV1);

        Mockito.when(pautaService.criarUmaNovaPauta(pautaRequestV1))
                .thenReturn(pautaResponseV1);

        given()
                .when()
                .contentType(ContentType.JSON)
                .body(request)
                .post("/api/v1/pauta")
                .then()
                .statusCode(201);


    }

}

