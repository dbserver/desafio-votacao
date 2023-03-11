package com.dbserver.controller;

import com.dbserver.model.dto.AgendaCreateDTO;
import com.dbserver.model.dto.VotingCreateDTO;
import com.dbserver.model.entity.Agenda;
import com.dbserver.model.entity.Voting;
import com.dbserver.repository.AgendaRepository;
import com.dbserver.repository.VoteRepository;
import com.dbserver.repository.VotingRepository;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.internal.matchers.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.TestPropertySource;

import java.time.LocalDateTime;
import java.util.UUID;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestPropertySource(locations = "classpath:test.properties")
class VotingControllerTest {

    @Autowired
    private AgendaRepository agendaRepository;
    @Autowired
    private VotingRepository votingRepository;

    @LocalServerPort
    private int port;

    private RequestSpecification requestSpec;

    @BeforeAll
    public void init() {
        String URL = "http://localhost:" + port + "/api/v1/voting";
        requestSpec = new RequestSpecBuilder()
                .setBaseUri(URL)
                .setContentType(ContentType.JSON)
                .build();
    }

    @Test
    void shouldCreateVotingAndStatus201() {
        Agenda agenda = agendaRepository.save(Agenda.builder().build());
        VotingCreateDTO votingCreateDTO = VotingCreateDTO.builder()
                .idAgenda(agenda.getId())
                .duration(6000l)
                .build();
        given(requestSpec)
                .body(votingCreateDTO)
                .post()
                .then()
                .statusCode(201)
                .body("id", notNullValue())
                .body("idAgenda", equalTo(agenda.getId()));
    }

    @Test
    void shouldThrowBadRequestExceptionWithoutRequiredFieldAndStatus400() {
        VotingCreateDTO votingCreateDTO = VotingCreateDTO.builder()
                .idAgenda(null)
                .duration(60000l)
                .build();
        given(requestSpec)
                .body(votingCreateDTO)
                .post()
                .then()
                .statusCode(400);
    }

    @Test
    void shouldThrowNotFoundExceptionWithNonExistentAgendaAndStatus404() {
        VotingCreateDTO votingCreateDTO = VotingCreateDTO.builder()
                .idAgenda("idAgenda")
                .duration(6000l)
                .build();
        given(requestSpec)
                .body(votingCreateDTO)
                .post()
                .then()
                .statusCode(404)
                .body("message", equalTo("Agenda not found"));
    }

    @Test
    void shouldGetVotingByIdAndStatus200() {
        Voting voting = votingRepository.save(Voting.builder().idAgenda(UUID.randomUUID().toString()).build());
        given(requestSpec)
                .get("/" + voting.getId())
                .then()
                .statusCode(200)
                .body("id", notNullValue());
    }

    @Test
    void shouldThrowNotFoundExceptionWithNonexistentVotingAndStatus404() {
        given(requestSpec)
                .get("/" + UUID.randomUUID())
                .then()
                .statusCode(404)
                .body("message", containsString("Voting not found"));
    }

}
