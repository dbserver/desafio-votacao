package com.db.polling.rest;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;

import com.db.polling.IntegrationTest;
import com.db.polling.api.dto.VotingSessionDTO;
import com.db.polling.api.dto.response.VoteResultResponse;
import com.db.polling.api.dto.response.VotingSessionResponse;
import com.db.polling.database.entity.AgendaEntity;
import com.db.polling.database.entity.AssociateEntity;
import com.db.polling.database.entity.VoteEntity;
import com.db.polling.database.entity.VotingSessionEntity;
import com.db.polling.database.repository.AgendaRepository;
import com.db.polling.database.repository.AssociateRepository;
import com.db.polling.database.repository.VoteRepository;
import com.db.polling.database.repository.VotingSessionRepository;
import com.db.polling.domain.enumeration.VoteEnum;
import io.restassured.http.ContentType;
import java.time.LocalDateTime;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

public class VotingSessionIntegrationTest extends IntegrationTest {

  protected static final String VOTING_SESSION_PATH = "api/v1/voting-sessions";

  protected static final String VOTING_SESSION_GET_PATH = "api/v1/voting-sessions/{id}";

  protected static final String VOTING_SESSION_RESULT_PATH = "api/v1/voting-sessions/{id}/result";

  @Autowired
  private VotingSessionRepository votingSessionRepository;

  @Autowired
  private AssociateRepository associateRepository;

  @Autowired
  private VoteRepository voteRepository;
  @Autowired
  private AgendaRepository agendaRepository;

  @Test
  void shouldCreateVotingSessionWithSuccess() {

    AgendaEntity agendaEntity = buildAgenda();

    VotingSessionDTO body = new VotingSessionDTO();
    body.setClosingTime(LocalDateTime.now().plusMinutes(10));
    body.setOpeningTime(LocalDateTime.now());
    body.setAgendaId(agendaEntity.getAgendaId());

    VotingSessionResponse response = given()
        .when()
        .contentType(ContentType.JSON)
        .body(body)
        .post(VOTING_SESSION_PATH)
        .then().log().ifError()
        .assertThat()
        .statusCode(HttpStatus.OK.value())
        .extract().as(VotingSessionResponse.class);

    assertEquals(response.getAgendaId(), body.getAgendaId());
    assertEquals(response.getOpeningTime(), body.getOpeningTime());
    assertEquals(response.getClosingTime(), body.getClosingTime());

  }

  @Test
  void shouldGetResultVotingSessionWithSuccess() {
    LocalDateTime now =  LocalDateTime.now();

    VotingSessionEntity votingSessionEntity = buildVotingSession(
        now, now.minusMinutes(20));
    buildVote(votingSessionEntity);

    VoteResultResponse response = given()
        .when()
        .contentType(ContentType.JSON)
        .pathParam("id", votingSessionEntity.getVotingSessionId())
        .get(VOTING_SESSION_RESULT_PATH)
        .then().log().ifError()
        .assertThat()
        .statusCode(HttpStatus.OK.value())
        .extract().as(VoteResultResponse.class);

    assertEquals(response.getNoVotes(), 0);
    assertEquals(response.getYesVotes(), 1);
    assertEquals(response.getTotalVotes(), 1);

  }

  @Test
  void shouldGetVotingSessionWithSuccess() {
    LocalDateTime now =  LocalDateTime.now();
    VotingSessionEntity votingSessionEntity = buildVotingSession(
        now.minusMinutes(5), now.minusMinutes(20));

    VotingSessionResponse response = given()
        .when()
        .contentType(ContentType.JSON)
        .pathParam("id", votingSessionEntity.getVotingSessionId())
        .get(VOTING_SESSION_GET_PATH)
        .then().log().ifError()
        .assertThat()
        .statusCode(HttpStatus.OK.value())
        .extract().as(VotingSessionResponse.class);

    assertEquals(response.getAgendaId(), votingSessionEntity.getAgendaEntity().getAgendaId());

  }

  private void buildVote(VotingSessionEntity votingSessionEntity) {

    VoteEntity voteEntity = new VoteEntity();
    voteEntity.setAssociateEntity(buildAssociate());
    voteEntity.setVotingSessionEntity(votingSessionEntity);
    voteEntity.setVote(VoteEnum.YES);

    voteRepository.saveAndFlush(voteEntity);
  }

  private VotingSessionEntity buildVotingSession(LocalDateTime closingTime, LocalDateTime openTime) {
    VotingSessionEntity votingSession = new VotingSessionEntity();
    votingSession.setAgendaEntity(buildAgenda());
    votingSession.setOpeningTime(openTime);
    votingSession.setClosingTime(closingTime);
    votingSession.setCreationDate(LocalDateTime.now());

    return votingSessionRepository.saveAndFlush(votingSession);
  }

  private AgendaEntity buildAgenda() {
    AgendaEntity agendaEntity = new AgendaEntity();
    agendaEntity.setTitle("title");
    agendaEntity.setDescription("description");
    agendaEntity.setCreationDate(LocalDateTime.now());

    return agendaRepository.saveAndFlush(agendaEntity);
  }

  private AssociateEntity buildAssociate() {
    AssociateEntity associateEntity = new AssociateEntity();
    associateEntity.setCpf("58418335025");
    associateEntity.setName("joao");
    associateEntity.setCreationDate(LocalDateTime.now());

    return associateRepository.saveAndFlush(associateEntity);
  }

}
