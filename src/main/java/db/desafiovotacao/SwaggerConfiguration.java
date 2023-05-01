package db.desafiovotacao;

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
                                        .name("Rayane Paiva")
                                        .email("rayane.paiva@db.tec.br")
                                        .url("https://github.com/systemagic-91")
                                )
                                .version("1.0.0")
                                .license(new License())
                                .termsOfService("")
                );
    }
}
