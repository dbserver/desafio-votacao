package com.db.polling;

import com.db.polling.database.entity.AssociateEntity;
import com.db.polling.database.repository.AssociateRepository;
import io.restassured.RestAssured;
import java.time.LocalDateTime;
import java.util.Arrays;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.context.TestExecutionListeners;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

@ActiveProfiles("test")
@SpringBootTest(classes = PollingApplication.class, webEnvironment = WebEnvironment.RANDOM_PORT)
@TestInstance(Lifecycle.PER_CLASS)
@Testcontainers
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Import(PostgreSQLContainerConfiguration.class)
@TestExecutionListeners(
    value = CleanDatabaseExecutionListener.class,
    mergeMode = TestExecutionListeners.MergeMode.MERGE_WITH_DEFAULTS
)
public abstract class IntegrationTest {

  @LocalServerPort
  private int localServerPort;

  @Container
  private static final PostgreSQLContainer<?> POSTGRESQL_CONTAINER = PostgreSQLContainerConfiguration.getContainer();

  @BeforeAll
  void containerProperties() {
    System.setProperty("spring.datasource.url", POSTGRESQL_CONTAINER.getJdbcUrl());
    System.setProperty("spring.datasource.username", POSTGRESQL_CONTAINER.getUsername());
    System.setProperty("spring.datasource.password", POSTGRESQL_CONTAINER.getPassword());
    RestAssured.port = localServerPort;
  }
}
