package br.tec.db.desafio.api.v1.controller;

import br.tec.db.desafio.api.v1.dto.sessao.request.SessaoParaCriarRequestV1;
import br.tec.db.desafio.api.v1.dto.sessao.request.SessaoParaSaberTotalVotosRequestV1;
import br.tec.db.desafio.api.v1.dto.sessao.request.SessaoParaVotarRequestV1;
import br.tec.db.desafio.api.v1.dto.sessao.response.SessaoCriadaResponseV1;
import br.tec.db.desafio.api.v1.dto.sessao.response.SessaoTotalVotosResponseV1;
import br.tec.db.desafio.api.v1.dto.sessao.response.SessaoVotadaResponseV1;
import br.tec.db.desafio.business.domain.enums.Voto;
import br.tec.db.desafio.business.service.SessaoService;
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
public class SessaoControllerV1Test {
    @MockBean
    SessaoService sessaoService;

    @LocalServerPort
    private int port;

    @BeforeEach
    void setup() {
        RestAssured.port = this.port;
    }

    @Test
    void devePersistirSessaoComSucesso() throws JsonProcessingException {
        SessaoParaCriarRequestV1 sessaoParaCriarRequestV1 =
                new SessaoParaCriarRequestV1(
                        "tema da pauta",
                        2L);
        SessaoCriadaResponseV1 sessaoCriadaResponseV1 =
                new SessaoCriadaResponseV1(
                        "tema da pauta"
                );
        String request = new ObjectMapper().writeValueAsString(sessaoParaCriarRequestV1);

        Mockito.when(sessaoService.criarUmaNovaSessao(sessaoParaCriarRequestV1))
                .thenReturn(sessaoCriadaResponseV1);

        given()
                .when()
                .contentType(ContentType.JSON)
                .body(request)
                .post("/api/v1/sessao")
                .then()
                .statusCode(201);


    }

    @Test
    void deveVotarEmUmaSessaoComSucesso() throws JsonProcessingException {
        SessaoParaVotarRequestV1 sessaoParaVotarRequestV1 =
                new SessaoParaVotarRequestV1(
                        Voto.SIM,
                        "tema da pauta");
        SessaoVotadaResponseV1 sessaoVotadaResponseV1 =
                new SessaoVotadaResponseV1(
                        Voto.SIM
                );
        String request = new ObjectMapper().writeValueAsString(sessaoParaVotarRequestV1);

        Mockito.when(sessaoService.votarEmUmaSessao(sessaoParaVotarRequestV1))
                .thenReturn(sessaoVotadaResponseV1);

        given()
                .when()
                .contentType(ContentType.JSON)
                .body(request)
                .post("/api/v1/sessao/votar")
                .then()
                .statusCode(200);


    }

    @Test
    void deveSaberTotalDeVotosDeUmaSessaoComSucesso() throws JsonProcessingException {
        SessaoParaSaberTotalVotosRequestV1 sessaoParaSaberTotalVotosRequestV1 =
                new SessaoParaSaberTotalVotosRequestV1(
                        "tema da pauta");
        SessaoTotalVotosResponseV1 sessaoTotalVotosResponseV1 =
                new SessaoTotalVotosResponseV1(
                        20
                );
        String request = new ObjectMapper().writeValueAsString(sessaoParaSaberTotalVotosRequestV1);

        Mockito.when(sessaoService.totalDeVotosDaSessao(sessaoParaSaberTotalVotosRequestV1))
                .thenReturn(sessaoTotalVotosResponseV1);

        given()
                .when()
                .contentType(ContentType.JSON)
                .body(request)
                .get("/api/v1/sessao")
                .then()
                .statusCode(200);


    }

}
