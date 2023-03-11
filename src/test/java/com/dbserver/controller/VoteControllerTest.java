package com.dbserver.controller;

import com.dbserver.model.dto.VoteCreatedDTO;
import com.dbserver.model.dto.VotingCreateDTO;
import com.dbserver.model.entity.Agenda;
import com.dbserver.repository.AgendaRepository;
import com.dbserver.service.VotingService;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.TestPropertySource;

import java.util.UUID;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestPropertySource(locations = "classpath:test.properties")
class VoteControllerTest {

    @Autowired
    private AgendaRepository agendaRepository;
    @Autowired
    private VotingService votingService;

    @LocalServerPort
    private int port;

    private RequestSpecification requestSpec;

    @BeforeAll
    public void init() {
        String URL = "http://localhost:" + port + "/api/v1/vote";
        requestSpec = new RequestSpecBuilder()
                .setBaseUri(URL)
                .setContentType(ContentType.JSON)
                .build();
    }

    @Test
    void shouldCreateVoteAndStatus201() {
        Agenda agenda = agendaRepository.save(Agenda.builder().build());
        votingService.create(VotingCreateDTO.builder().duration(60000l).idAgenda(agenda.getId()).build());
        VoteCreatedDTO voteCreatedDTO = VoteCreatedDTO.builder()
                .vote(true)
                .idAgenda(agenda.getId())
                .cpf(UUID.randomUUID().toString())
                .build();
        given(requestSpec)
                .body(voteCreatedDTO)
                .post()
                .then()
                .statusCode(201)
                .body("id", notNullValue())
                .body("idAgenda", equalTo(agenda.getId()));
    }

    @Test
    void shouldThrowConflictExceptionWithEndedVotingAndStatus409() {
        Agenda agenda = agendaRepository.save(Agenda.builder().build());
        votingService.create(VotingCreateDTO.builder().duration(6l).idAgenda(agenda.getId()).build());
        VoteCreatedDTO voteCreatedDTO = VoteCreatedDTO.builder()
                .vote(true)
                .idAgenda(agenda.getId())
                .cpf(UUID.randomUUID().toString())
                .build();
        given(requestSpec)
                .body(voteCreatedDTO)
                .post()
                .then()
                .statusCode(409)
                .body("message", equalTo("Voting has already ended"));
    }

    @Test
    void shouldThrowNotFoundExceptionWithNonexistentAgendaAndStatus404() {
        VoteCreatedDTO voteCreatedDTO = VoteCreatedDTO.builder()
                .vote(true)
                .idAgenda(UUID.randomUUID().toString())
                .cpf(UUID.randomUUID().toString())
                .build();
        given(requestSpec)
                .body(voteCreatedDTO)
                .post()
                .then()
                .statusCode(404)
                .body("message", containsString("Voting not found for idAgenda"));
    }

}
