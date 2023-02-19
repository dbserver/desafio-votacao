package br.com.dbserver.votacao.stubs;

import br.com.dbserver.votacao.v1.dto.request.PautaRequest;
import br.com.dbserver.votacao.v1.dto.response.PautaResponse;
import br.com.dbserver.votacao.v1.entity.Pauta;
import br.com.dbserver.votacao.v1.enums.PautaStatusEnum;

import java.time.LocalDateTime;
import java.util.ArrayList;

public interface PautaStub {

	static Pauta construirPauta() {
		return Pauta.builder()
				.id(1L)
				.descricao("Teste")
				.inicio(LocalDateTime.now().plusHours(1))
				.fim(LocalDateTime.now().plusHours(4))
				.votos(new ArrayList<>())
				.status(PautaStatusEnum.AGUARDANDO_RESULTADO)
				.build();
	}
	static PautaRequest construirPautaRequest() {
		return PautaRequest.builder()
				.assembleiaId(1L)
				.descricao("Teste")
				.inicio(LocalDateTime.now().plusHours(1))
				.fim(LocalDateTime.now().plusHours(4))
				.build();
	}

	static PautaResponse construirPautaResponse() {
		return PautaResponse.builder()
				.id(1L)
				.descricao("Teste")
				.inicio(LocalDateTime.now().plusHours(1))
				.fim(LocalDateTime.now().plusHours(4))
				.votos(new ArrayList<>())
				.status(PautaStatusEnum.AGUARDANDO_RESULTADO)
				.build();
	}
}