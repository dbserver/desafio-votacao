package com.db.desafiovotacao.restassured;

import com.db.desafiovotacao.api.record.CreateAgendaResponseRecord;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

import static io.restassured.RestAssured.given;

@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
)
public class AgendaControllerTest {
    @LocalServerPort
    private Integer port;

    @Test
    public void createAnAgenda() {
        CreateAgendaResponseRecord createAgendaResponseRecord=given()
                .param("name","Agenda Test")
                .when()
                .request("POST", "http://localhost:"+port+"/agendas")
                .then().statusCode(200)
                .extract()
                .as(CreateAgendaResponseRecord.class);

    }
}
