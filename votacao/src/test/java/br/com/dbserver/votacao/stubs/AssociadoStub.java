package br.com.dbserver.votacao.stubs;

import br.com.dbserver.votacao.v1.dto.request.AssociadoRequest;
import br.com.dbserver.votacao.v1.dto.response.AssociadoResponse;
import br.com.dbserver.votacao.v1.entity.Associado;
import br.com.dbserver.votacao.v1.enums.StatusUsuarioEnum;

public interface AssociadoStub {
	static AssociadoRequest construirAssociadoRequest(StatusUsuarioEnum status){
		return AssociadoRequest.builder()
				.documento("90015955028")
				.nome("Usuario Teste")
				.status(status)
				.build();
	}

	static AssociadoResponse construirAssociadoResponse(StatusUsuarioEnum status){
		return AssociadoResponse.builder()
				.id(1L)
				.documento("90015955028")
				.nome("Usuario Teste")
				.status(status)
				.build();
	}

	static Associado construirAssociado(){
		return Associado.builder()
				.id(1L)
				.documento("90015955028")
				.nome("Usuario Teste")
				.status(StatusUsuarioEnum.PODE_VOTAR)
				.build();
	}
}
