package br.com.dbserver.votacao.stubs;

import br.com.dbserver.votacao.v1.dto.request.AssembleiaRequest;
import br.com.dbserver.votacao.v1.dto.response.AssembleiaResponse;
import br.com.dbserver.votacao.v1.entity.Assembleia;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public interface AssembleiaStub {

	LocalDateTime DATA_INICIO = LocalDateTime.now().plusHours(1);
	LocalDateTime DATA_FIM = LocalDateTime.now().plusHours(1);

	static AssembleiaRequest construirAssembleiaRequest() {
		return AssembleiaRequest.builder()
				.inicio(DATA_INICIO)
				.fim(DATA_FIM)
				.build();
	}

	static Assembleia construirAssembleiaComId() {
		return Assembleia.builder()
				.id(1L)
				.inicio(DATA_INICIO)
				.fim(DATA_FIM)
				.pautas(new ArrayList<>())
				.build();
	}

	static AssembleiaResponse construirAssembleiaResponse() {
		return AssembleiaResponse.builder()
				.id(1L)
				.inicio(DATA_INICIO)
				.fim(DATA_FIM)
				.pautas(List.of())
				.build();
	}

}
