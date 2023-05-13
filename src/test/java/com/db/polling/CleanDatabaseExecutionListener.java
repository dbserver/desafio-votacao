package com.db.polling;

import java.util.Map;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.test.context.TestContext;
import org.springframework.test.context.TestExecutionListener;

public class CleanDatabaseExecutionListener implements TestExecutionListener {

  @Override
  public void beforeTestMethod(TestContext context) {
    Map<String, JpaRepository> repositoryMap = getContextRepositories(context);

    cleanTables(repositoryMap);
  }

  private Map<String, JpaRepository> getContextRepositories(TestContext context) {
    return context.getApplicationContext().getBeansOfType(JpaRepository.class);
  }

  private void cleanTables(Map<String, JpaRepository> repositoryList) {
    repositoryList.forEach((key, value) -> {
      value.deleteAll();
      value.flush();
    });
  }

}
