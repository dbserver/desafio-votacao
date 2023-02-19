package br.com.dbserver.votacao.v1.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static br.com.dbserver.votacao.SqlProvider.insertAssembleia;
import static br.com.dbserver.votacao.SqlProvider.resetarDB;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
@DisplayName("Assembleia Controller")
class AssembleiaControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Test
	@Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD, scripts = resetarDB)
	@DisplayName("POST/SUCESSO deve criar uma Assembleia")
	public void testCriarAssembleia() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.post("/v1/assembleia")
						.contentType(MediaType.APPLICATION_JSON)
						.content("{\n" +
								"  \"inicio\": \"2030-02-19T16:35:39.204Z\",\n" +
								"  \"fim\": \"2030-03-19T16:35:39.204Z\"\n" +
								"}"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.inicio").value("2030-02-19T16:35:39.204"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.fim").value("2030-03-19T16:35:39.204"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1))
				.andExpect(status().isCreated());
	}

	@Test
	@Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD, scripts = resetarDB)
	@DisplayName("POST/Error nao pode criar uma Assembleia com datas divergentes")
	public void testCriarAssembleiaError() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.post("/v1/assembleia")
						.contentType(MediaType.APPLICATION_JSON)
						.content("{\n" +
								"  \"inicio\": \"2023-04-19T16:35:39.204Z\",\n" +
								"  \"fim\": \"2023-03-19T16:35:39.204Z\"\n" +
								"}"))
				.andExpect(status().isBadRequest());
	}

	@Test
	@SqlGroup({
			@Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = insertAssembleia),
			@Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD, scripts = resetarDB)
	})
	@DisplayName("GET/Sucesso deve buscar assembleias paginadas com sucesso")
	public void testBuscarAssembleia() throws Exception {
		mockMvc.perform(get("/v1/assembleia")
						.content("{\n" +
								"  \"page\": 0,\n" +
								"  \"size\": 1,\n" +
								"  \"sort\": [\n" +
								"    \"id\"\n" +
								"  ]\n" +
								"}")
						.accept(MediaType.APPLICATION_JSON_VALUE)
						.contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(MockMvcResultMatchers.jsonPath("$.lista[0].id").value(1))
				.andExpect(MockMvcResultMatchers.jsonPath("$.totalPaginas").value(1))
				.andExpect(MockMvcResultMatchers.jsonPath("$.lista[0].fim").value("2023-03-19T13:35:39.204"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.lista[0].inicio").value("2023-02-19T13:35:39.204"))
				.andExpect(status().isOk());
	}
}