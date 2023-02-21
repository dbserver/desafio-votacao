package br.com.dbserver.votacao.stubs;

import br.com.dbserver.votacao.v1.dto.request.VotoRequest;
import br.com.dbserver.votacao.v1.entity.Associado;
import br.com.dbserver.votacao.v1.entity.Pauta;
import br.com.dbserver.votacao.v1.entity.Voto;
import br.com.dbserver.votacao.v1.enums.VotoEnum;

public interface VotoStub {

	static VotoRequest criarVotoRequest() {
		return VotoRequest.builder()
				.valor(VotoEnum.SIM)
				.documentoAssociado("90015955028")
				.pautaId(1L)
				.build();
	}

	static Voto criarVoto(Pauta pauta, Associado associado) {
		return Voto.builder()
				.id(1L)
				.valor(VotoEnum.SIM)
				.pauta(pauta)
				.associado(associado)
				.build();
	}

}
