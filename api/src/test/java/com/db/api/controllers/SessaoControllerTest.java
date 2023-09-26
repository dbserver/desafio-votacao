package com.db.api.controllers;

import com.db.api.stubs.SessaoStub;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;

import static com.db.api.SqlProvider.inserirPauta;
import static com.db.api.SqlProvider.resetarDB;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItem;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class SessaoControllerTest {

    private final String URL = "/api/v1/sessoes";
    @LocalServerPort
    private int port;

    @BeforeEach
    void setup() {
        RestAssured.port = this.port;
    }

    @Test
    @DisplayName("Deve iniciar uma sessão de votação com sucesso")
    @SqlGroup({
            @Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = inserirPauta),
            @Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD, scripts = resetarDB)
    })
    void deveIniciarSessaoComSucesso() {
        RestAssured.given()
                .contentType(ContentType.JSON)
                .body(SessaoStub.gerarSessaoDtoValida())
                .when()
                .post(URL + "/iniciarVotacao")
                .then()
                .contentType(ContentType.TEXT)
                .statusCode(HttpStatus.CREATED.value())
                .body(equalTo("Iniciada votação da pauta: " + SessaoStub.gerarSessaoDtoValida().getPauta().getTitulo()));
    }

    @Test
    @DisplayName("Deve retornar um exceção ao iniciar uma sessão de votação com uma data de encerramento invalida")
    void testIniciarSessaoComDataDeEncerramentoAnteriorADataAtual() {
        given()
                .contentType("application/json")
                .body(SessaoStub.gerarSessaoDtoDataEncerramentoInvalida())
                .when()
                .post(URL + "/iniciarVotacao")
                .then()
                .statusCode(HttpStatus.UNPROCESSABLE_ENTITY.value())
                .body("mensagem", equalTo("Erro de validação"))
                .body("detalhes", hasItem("deve ser uma data futura"));
    }

    @Test
    @DisplayName("Deve retornar um exceção ao iniciar uma sessão para uma pauta inexistente")
    void testIniciarSessaoPautaInexistente() {
        given()
                .contentType("application/json")
                .body(SessaoStub.gerarSessaoDtoValida())
                .when()
                .post(URL + "/iniciarVotacao")
                .then()
                .statusCode(HttpStatus.NOT_FOUND.value())
                .body("mensagem", equalTo("Registro não encontrado"))
                .body("detalhes", hasItem("A pauta requerida não foi encontrado!"));
    }
}
