package br.com.dbserver.votacao.stubs;

import br.com.dbserver.votacao.v1.dto.request.AssembleiaRequest;
import br.com.dbserver.votacao.v1.dto.response.AssembleiaResponse;
import br.com.dbserver.votacao.v1.entity.Assembleia;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public interface AssembleiaStub {

	static AssembleiaRequest construirAssembleiaRequest() {
		return AssembleiaRequest.builder()
				.inicio(LocalDateTime.now().plusHours(1))
				.fim(LocalDateTime.now().plusHours(4))
				.build();
	}

	static Assembleia construirAssembleiaComId() {
		return Assembleia.builder()
				.id(1L)
				.inicio(LocalDateTime.now().plusHours(1))
				.fim(LocalDateTime.now().plusHours(4))
				.pautas(new ArrayList<>())
				.build();
	}

	static AssembleiaResponse construirAssembleiaResponse() {
		return AssembleiaResponse.builder()
				.id(1L)
				.inicio(LocalDateTime.now().plusHours(1))
				.fim(LocalDateTime.now().plusHours(4))
				.pautas(List.of())
				.build();
	}

}
