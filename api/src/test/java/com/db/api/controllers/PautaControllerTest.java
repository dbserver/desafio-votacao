package com.db.api.controllers;

import com.db.api.stubs.PautaStub;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItem;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class PautaControllerTest {

    private final String URL = "/api/v1/pautas";
    @LocalServerPort
    private int port;

    @BeforeEach
    void setup() {
        RestAssured.port = this.port;
    }

    @Test
    @DisplayName("Deve cadastrar pauta com sucesso")
    void deveCadastrarPautaComSucesso() {
        RestAssured.given()
                .contentType(ContentType.JSON)
                .body(PautaStub.gerarPautaDtoValida())
                .when()
                .post(URL + "/cadastrar")
                .then()
                .contentType(ContentType.JSON)
                .statusCode(HttpStatus.CREATED.value())
                .body("titulo", equalTo(PautaStub.gerarPautaDtoValida().getTitulo()))
                .body("descricao", equalTo(PautaStub.gerarPautaDtoValida().getDescricao()));
    }

    @Test
    @DisplayName("Deve retornar um exceção ao tentar criar pauta com título em branco")
    void testCriarPautaComTituloEmBranco() {
        given()
                .contentType("application/json")
                .body(PautaStub.gerarPautaDtoTituloVazio())
                .when()
                .post(URL + "/cadastrar")
                .then()
                .statusCode(HttpStatus.UNPROCESSABLE_ENTITY.value())
                .body("mensagem", equalTo("Erro de validação"))
                .body("detalhes", hasItem("Por favor, informe o titulo da pauta."));
    }
}
