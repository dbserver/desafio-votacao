package com.db.polling.rest;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertEquals;

import com.db.polling.IntegrationTest;
import com.db.polling.api.constants.ApiConstants;
import com.db.polling.api.dto.AssociateDTO;
import com.db.polling.api.dto.response.AssociateResponse;
import com.db.polling.database.entity.AssociateEntity;
import com.db.polling.database.repository.AssociateRepository;
import io.restassured.http.ContentType;
import java.time.LocalDateTime;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

public class AssociateIntegrationTest extends IntegrationTest {

  protected static final String ASSOCIATE_PATH = "/api/v1/associates";

  protected static final String ASSOCIATE_GET_PATH = "/api/v1/associates/{id}";

  @Autowired
  private AssociateRepository associateRepository;

  @Test
  void shouldCreateAssociateWithSuccess() {

    AssociateDTO body = new AssociateDTO();
    body.setCpf("59707271051");
    body.setName("joao");

    AssociateResponse response = given()
        .when()
        .contentType(ContentType.JSON)
        .body(body)
        .post(ASSOCIATE_PATH)
        .then().log().ifError()
        .assertThat()
        .statusCode(HttpStatus.OK.value())
        .extract().as(AssociateResponse.class);

    assertEquals(response.getCpf(), body.getCpf());
    assertEquals(response.getName(), body.getName());

  }

  @Test
  void shouldGetAllAssociateWithSuccess() {
    AssociateEntity associateEntity = buildAssociate();

    List<AssociateResponse> response = given()
        .when()
        .contentType(ContentType.JSON)
        .queryParam("page", "0")
        .queryParam("size", "1")
        .get(ASSOCIATE_PATH)
        .then().log().ifError()
        .assertThat()
        .statusCode(HttpStatus.OK.value())
        .header(ApiConstants.TOTAL_ELEMENTS_HEADER, equalTo("1"))
        .header(ApiConstants.TOTAL_PAGES_HEADER, equalTo("0"))
        .header(ApiConstants.HAS_NEXT_HEADER, equalTo("false"))
        .extract().jsonPath().getList("", AssociateResponse.class);

    assertThat(response).usingRecursiveComparison().isEqualTo(List.of(associateEntity));

  }

  @Test
  void shouldGetAssociateWithSuccess() {
    AssociateEntity associateEntity = buildAssociate();

    AssociateResponse response = given()
        .when()
        .contentType(ContentType.JSON)
        .pathParam("id", associateEntity.getAssociateId())
        .get(ASSOCIATE_GET_PATH)
        .then().log().ifError()
        .assertThat()
        .statusCode(HttpStatus.OK.value())
        .extract().as(AssociateResponse.class);

    assertEquals(associateEntity.getCpf(), response.getCpf());
    assertEquals(associateEntity.getName(), response.getName());

  }

  private AssociateEntity buildAssociate() {
    AssociateEntity associateEntity = new AssociateEntity();
    associateEntity.setCpf("58418335025");
    associateEntity.setName("joao");
    associateEntity.setCreationDate(LocalDateTime.now());

    return associateRepository.saveAndFlush(associateEntity);
  }

}
