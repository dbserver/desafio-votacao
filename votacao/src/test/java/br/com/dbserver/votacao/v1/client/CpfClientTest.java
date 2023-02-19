package br.com.dbserver.votacao.v1.client;

import br.com.dbserver.votacao.Config.TestConfig;
import feign.Feign;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;
import feign.mock.HttpMethod;
import feign.mock.MockClient;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.openfeign.support.SpringMvcContract;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = TestConfig.class)
class CpfClientTest {
	@Value("${api.validacao.cpf.url}")
	private String BASE_URL;
	private CpfClient cpfClient;

	private static final String RESPONSE_POSTS = "{\n" +
			"\"status\": 1,\n" +
			"\"cpf\": \"812.692.380-60\",\n" +
			"\"nome\": \"Test Token\",\n" +
			"\"nascimento\": \"31/12/1999\",\n" +
			"\"mae\": \"Maria Jose\",\n" +
			"\"genero\": \"M\",\n" +
			"\"situacao\": \"Regular\",\n" +
			"\"pacoteUsado\": 9,\n" +
			"\"saldo\": 123,\n" +
			"\"consultaID\": \"11bb22cc33dd44ee\",\n" +
			"\"delay\": 0.3\n" +
			"}";

	@Test
	@DisplayName("Teste do CpfClient")
	void buscarCpf() {
		String cpf = "81269238060";
		this.buildFeignClient(new MockClient().ok(
				HttpMethod.GET,
				BASE_URL.concat("/5ae973d7a997af13f0aaf2bf60e65803/9/" + cpf),
				RESPONSE_POSTS));
		CpfResponse response = cpfClient.buscarCpf("81269238060");

		assertEquals("Regular", response.getSituacao());
	}

	private void buildFeignClient(MockClient mockClient) {
		cpfClient = Feign.builder()
				.client(mockClient)
				.encoder(new JacksonEncoder())
				.decoder(new JacksonDecoder())
				.contract(new SpringMvcContract())
				.target(CpfClient.class, BASE_URL);
	}
}