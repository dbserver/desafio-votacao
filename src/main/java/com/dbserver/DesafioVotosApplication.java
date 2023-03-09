package com.dbserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.config.EnableMongoAuditing;

@SpringBootApplication
@EnableMongoAuditing
public class DesafioVotosApplication {

	public static void main(String[] args) {
		SpringApplication.run(DesafioVotosApplication.class, args);
	}

}
