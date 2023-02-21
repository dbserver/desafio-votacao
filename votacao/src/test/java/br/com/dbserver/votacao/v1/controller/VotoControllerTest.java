package br.com.dbserver.votacao.v1.controller;

import br.com.dbserver.votacao.rabbitmq.config.RabbitMQConection;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.test.web.servlet.MockMvc;

import static br.com.dbserver.votacao.SqlProvider.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@DisplayName("Voto Controller")
class VotoControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	RabbitMQConection rabbitMQConection;

	@Test
	@SqlGroup({
			@Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = insertAssociado),
			@Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = insertPauta),
			@Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD, scripts = resetarDB)
	})
	@DisplayName("POST/SUCESSO deve criar um Voto")
	void votar() throws Exception {
		mockMvc.perform(post("/v1/voto")
						.contentType(MediaType.APPLICATION_JSON)
						.content("""
								{
								  "pautaId": 1,
								  "documentoAssociado": "90015955028",
								  "valor": "SIM"
								}
								"""))
				.andExpect(status().isCreated());
	}

	@Test
	@SqlGroup({
			@Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = insertVoto),
			@Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD, scripts = resetarDB)
	})
	@DisplayName("POST/Erro lancar excesao quando um Voto for em pauta que ja expirou")
	void votarErro() throws Exception {
		mockMvc.perform(post("/v1/voto")
						.contentType(MediaType.APPLICATION_JSON)
						.content("""
								{
								  "pautaId": 2,
								  "documentoAssociado": "90015955028",
								  "valor": "SIM"
								}
								"""))
				.andExpect(status().isBadRequest());
	}

	@Test
	@SqlGroup({
			@Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = insertVoto),
			@Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD, scripts = resetarDB)
	})
	@DisplayName("POST/Erro lancar excesao quando um Associado tentar votar duplicado")
	void votarErroQuandoVotoDuplicado() throws Exception {
		mockMvc.perform(post("/v1/voto")
						.contentType(MediaType.APPLICATION_JSON)
						.content("""
								{
								  "pautaId": 1,
								  "documentoAssociado": "90015955028",
								  "valor": "SIM"
								}
								"""))
				.andExpect(status().isBadRequest());
	}
}