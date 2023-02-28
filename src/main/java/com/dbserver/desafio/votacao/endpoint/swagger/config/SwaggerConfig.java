package com.dbserver.desafio.votacao.endpoint.swagger.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI greetingApi() {
        return new OpenAPI()
                .info(info());
    }

    private Info info() {
        return new Info()
                .title("Desafio Votação - DbServer")
                .description("API de votação de Pautas de Assembleias");
    }
}
