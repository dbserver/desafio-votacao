package com.db.polling;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class PollingApplication {

  public static void main(String[] args) {
    SpringApplication.run(PollingApplication.class, args);
  }

}
