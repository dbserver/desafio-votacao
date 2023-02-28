package com.dbserver.desafio.valida.cpf;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.time.Clock;
import java.time.ZoneId;


@SpringBootApplication(scanBasePackages = "com.dbserver.desafio.votacao")
@EnableJpaRepositories("com.dbserver.desafio.votacao")
@EntityScan("com.dbserver.desafio.votacao")
public class Application {

    public static void main(final String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public Clock clock() {
        return Clock.system(ZoneId.of("America/Sao_Paulo"));
    }
}