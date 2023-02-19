package br.com.dbserver.votacao.v1.mapper;

import br.com.dbserver.votacao.v1.dto.response.PautaPaginadaResponse;
import br.com.dbserver.votacao.v1.entity.Pauta;
import org.springframework.data.domain.Page;

public class MapperPautaPaginada {
	public static PautaPaginadaResponse toPautaPaginada(Page<Pauta> paginas) {
		return PautaPaginadaResponse.builder()
				.totalPaginas(paginas.getTotalPages())
				.pataResponses(paginas.getContent().stream()
						.map(MapperPauta.INSTANCE::pautaToResponse)
						.toList())
				.build();
	}
}
