package com.db.polling.rest;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;

import com.db.polling.IntegrationTest;
import com.db.polling.api.dto.VoteDTO;
import com.db.polling.api.dto.response.VoteResponse;
import com.db.polling.database.entity.AgendaEntity;
import com.db.polling.database.entity.AssociateEntity;
import com.db.polling.database.entity.VotingSessionEntity;
import com.db.polling.database.repository.AgendaRepository;
import com.db.polling.database.repository.AssociateRepository;
import com.db.polling.database.repository.VotingSessionRepository;
import com.db.polling.domain.enumeration.VoteEnum;
import io.restassured.http.ContentType;
import java.time.LocalDateTime;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

public class VoteIntegrationTest extends IntegrationTest {

  protected static final String VOTE_PATH = "/api/v1/vote/{associateId}";

  @Autowired
  private VotingSessionRepository votingSessionRepository;

  @Autowired
  private AssociateRepository associateRepository;

  @Autowired
  private AgendaRepository agendaRepository;

  @Test
  void shouldVoteWithSuccess() {
    AssociateEntity associateEntity = buildAssociate();
    VotingSessionEntity votingSession = buildVotingSession();

    VoteDTO body = new VoteDTO();
    body.setVote(VoteEnum.YES);
    body.setAssociateId(associateEntity.getAssociateId());
    body.setVotingSessionId(votingSession.getVotingSessionId());

    VoteResponse response = given()
        .when()
        .contentType(ContentType.JSON)
        .pathParam("associateId", associateEntity.getAssociateId())
        .body(body)
        .post(VOTE_PATH)
        .then().log().ifError()
        .assertThat()
        .statusCode(HttpStatus.OK.value())
        .extract().as(VoteResponse.class);

    assertEquals(response.getVote(), body.getVote());

  }

  private VotingSessionEntity buildVotingSession() {
    VotingSessionEntity votingSession = new VotingSessionEntity();
    votingSession.setAgendaEntity(buildAgenda());
    votingSession.setOpeningTime(LocalDateTime.now().minusMinutes(20));
    votingSession.setClosingTime(LocalDateTime.now().plusMinutes(10));
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
