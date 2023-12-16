package com.challenge.votation;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "Agendas Api", version = "1", description = "Api for managing votes for an agenda"))
public class VotationApplication {

	public static void main(String[] args) {
		SpringApplication.run(VotationApplication.class, args);
	}

}
