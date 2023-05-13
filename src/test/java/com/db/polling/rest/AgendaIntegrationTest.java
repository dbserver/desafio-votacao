package com.db.polling.rest;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertEquals;

import com.db.polling.IntegrationTest;
import com.db.polling.api.constants.ApiConstants;
import com.db.polling.api.dto.AgendaDTO;
import com.db.polling.api.dto.response.AgendaResponse;
import com.db.polling.database.entity.AgendaEntity;
import com.db.polling.database.repository.AgendaRepository;
import io.restassured.http.ContentType;
import java.time.LocalDateTime;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

public class AgendaIntegrationTest extends IntegrationTest {

  protected static final String AGENDA_PATH = "/api/v1/agendas";

  protected static final String AGENDA_GET_PATH = "/api/v1/agendas/{id}";

  @Autowired
  private AgendaRepository agendaRepository;

  @Test
  void shouldCreateAgendaWithSuccess() {

    AgendaDTO body = new AgendaDTO();
    body.setTitle("title");
    body.setDescription("description");

    AgendaResponse response = given()
        .when()
        .contentType(ContentType.JSON)
        .body(body)
        .post(AGENDA_PATH)
        .then().log().ifError()
        .assertThat()
        .statusCode(HttpStatus.OK.value())
        .extract().as(AgendaResponse.class);

    assertEquals(response.getDescription(), body.getDescription());
    assertEquals(response.getTitle(), body.getTitle());

  }

  @Test
  void shouldGetAllAgendaWithSuccess() {
    AgendaEntity agendaEntity = buildAgenda();

    List<AgendaResponse> response = given()
        .when()
        .contentType(ContentType.JSON)
        .queryParam("page", "0")
        .queryParam("size", "1")
        .get(AGENDA_PATH)
        .then().log().ifError()
        .assertThat()
        .statusCode(HttpStatus.OK.value())
        .header(ApiConstants.TOTAL_ELEMENTS_HEADER, equalTo("1"))
        .header(ApiConstants.TOTAL_PAGES_HEADER, equalTo("0"))
        .header(ApiConstants.HAS_NEXT_HEADER, equalTo("false"))
        .extract().jsonPath().getList("", AgendaResponse.class);

    assertThat(response).usingRecursiveComparison().isEqualTo(List.of(agendaEntity));

  }

  @Test
  void shouldGetAgendaWithSuccess() {
    AgendaEntity agendaEntity = buildAgenda();

    AgendaResponse response = given()
        .when()
        .contentType(ContentType.JSON)
        .pathParam("id", agendaEntity.getAgendaId())
        .get(AGENDA_GET_PATH)
        .then().log().ifError()
        .assertThat()
        .statusCode(HttpStatus.OK.value())
        .extract().as(AgendaResponse.class);

    assertEquals(agendaEntity.getTitle(), response.getTitle());
    assertEquals(agendaEntity.getDescription(), response.getDescription());

  }

  private AgendaEntity buildAgenda() {
    AgendaEntity agendaEntity = new AgendaEntity();
    agendaEntity.setTitle("title");
    agendaEntity.setDescription("description");
    agendaEntity.setCreationDate(LocalDateTime.now());

    return agendaRepository.saveAndFlush(agendaEntity);
  }


}
