package com.desafio.projeto_votacao;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan("com.desafio.projeto_votacao.entity")
@EnableJpaRepositories("com.desafio.projeto_votacao.repository")
public class ProjetoVotacaoApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProjetoVotacaoApplication.class);
	}
	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

}