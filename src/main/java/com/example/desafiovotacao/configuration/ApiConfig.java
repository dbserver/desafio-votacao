package com.example.desafiovotacao.configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import org.springdoc.core.configuration.SpringDocConfiguration;


@OpenAPIDefinition(
        info = @Info(
                title = "Desafio Votação DB",
                description = "Uma API que cadastra pauta, inicializa sessões e conta a votação de associados",
                version = "1.1.0",
                contact = @Contact(
                        name = "Magno Korzekwa",
                        email = "magnokorzekwa@gmail.com"
                )
        )
)
public class ApiConfig extends SpringDocConfiguration {
}

