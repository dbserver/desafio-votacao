package com.db.desafio.configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfiguration {

    @Bean
    public OpenAPI apiInfo() {
        return new OpenAPI()
                .info(
                        new Info()
                                .title("Desafio Votação API")
                                .description("")
                                .contact(new Contact()
                                        .name("Renan Camara")
                                        .email("renan.camara@db.tec.br")
                                        .url("https://github.com/Renan-Alyson-Camara")
                                )
                                .version("1.0.0")
                                .license(new License())
                                .termsOfService("")
                );
    }
}