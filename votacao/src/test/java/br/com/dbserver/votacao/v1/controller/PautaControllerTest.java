package br.com.dbserver.votacao.v1.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.test.web.servlet.MockMvc;

import static br.com.dbserver.votacao.SqlProvider.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@DisplayName("Pauta Controller")
class PautaControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Test
	@SqlGroup({
			@Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = insertAssembleia),
			@Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD, scripts = resetarDB)
	})
	@DisplayName("POST/SUCESSO deve criar uma Pauta")
	void criarPauta() throws Exception {

		mockMvc.perform(post("/v1/pauta")
						.contentType(MediaType.APPLICATION_JSON)
						.content("""
								{
								  "assembleiaId": 1,
								  "descricao": "Teste",
								  "inicio": "2030-02-19T20:40:09.571Z",
								  "fim": "2030-04-19T20:40:09.571Z"
								}
								"""))
				.andExpect(jsonPath("$.id").value(1))
				.andExpect(jsonPath("$.descricao").value("Teste"))
				.andExpect(jsonPath("$.inicio").value("2030-02-19T20:40:09.571"))
				.andExpect(jsonPath("$.fim").value("2030-04-19T20:40:09.571"))
				.andExpect(status().isCreated());
	}

	@Test
	@SqlGroup({
			@Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = insertAssembleia),
			@Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD, scripts = resetarDB)
	})
	@DisplayName("POST/Erro não deve criar uma Pauta com data inicial menor que o dia atual")
	void criarPautaErro() throws Exception {

		mockMvc.perform(post("/v1/pauta")
						.contentType(MediaType.APPLICATION_JSON)
						.content("""
								{
								  "assembleiaId": 1,
								  "descricao": "Teste",
								  "inicio": "2022-02-19T20:40:09.571Z",
								  "fim": "2030-04-19T20:40:09.571Z"
								}
								"""))
				.andExpect(status().isBadRequest());
	}

	@Test
	@SqlGroup({
			@Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = insertAssembleia),
			@Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD, scripts = resetarDB)
	})
	@DisplayName("POST/Erro não deve criar uma Pauta com data inicial maior que a final")
	void criarPautaErroComDataDivergente() throws Exception {

		mockMvc.perform(post("/v1/pauta")
						.contentType(MediaType.APPLICATION_JSON)
						.content("""
								{
								  "assembleiaId": 1,
								  "descricao": "Teste",
								  "inicio": "2030-02-19T20:40:09.571Z",
								  "fim": "2028-04-19T20:40:09.571Z"
								}
								"""))
				.andExpect(status().isBadRequest());
	}

	@Test
	@SqlGroup({
			@Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = insertPauta),
			@Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD, scripts = resetarDB)
	})
	@DisplayName("GET/Sucesso deve buscar pauta pelo ID")
	void buscarPautaPeloId() throws Exception {
		mockMvc.perform(get("/v1/pauta/{id}", 1)
						.accept(MediaType.APPLICATION_JSON_VALUE)
						.contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(jsonPath("$.id").value(1))
				.andExpect(jsonPath("$.descricao").value("Nova Pauta"))
				.andExpect(jsonPath("$.votoSim").value(0))
				.andExpect(jsonPath("$.votoNao").value(0))
				.andExpect(jsonPath("$.status").value("Aguardando resultado"))
				.andExpect(status().isOk());
	}

	@Test
	@SqlGroup({
			@Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = insertPauta),
			@Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD, scripts = resetarDB)
	})
	@DisplayName("GET/Erro ao buscar pauta pelo ID inexistente")
	void buscarPautaPeloIdInexistente() throws Exception {
		mockMvc.perform(get("/v1/pauta/{id}", 5)
						.contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(status().isNotFound());
	}

	@Test
	@SqlGroup({
			@Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = insertPauta),
			@Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD, scripts = resetarDB)
	})
	@DisplayName("GET/Sucesso ao buscar pautas paginadas")
	void buscarTodasPautas() throws Exception {
		mockMvc.perform(get("/v1/pauta")
						.content("""
								{
								  "page": 0,
								  "size": 1,
								  "sort": [
								    "id"
								  ]
								}""")
						.accept(MediaType.APPLICATION_JSON_VALUE)
						.contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(jsonPath("$.totalPaginas").value(1))
				.andExpect(jsonPath("$.lista[0].id").value(1))
				.andExpect(jsonPath("$.lista[0].descricao").value("Nova Pauta"))
				.andExpect(status().isOk());
	}
}