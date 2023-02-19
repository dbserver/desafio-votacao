package br.com.dbserver.votacao.v1.mapper;

import br.com.dbserver.votacao.v1.dto.response.AssembleiaPaginadaResponse;
import br.com.dbserver.votacao.v1.entity.Assembleia;
import org.springframework.data.domain.Page;

public class MapperAssembleiaPaginada {

	public static AssembleiaPaginadaResponse toAssembleiaPaginada(Page<Assembleia> paginas) {
		return AssembleiaPaginadaResponse.builder()
				.totalPaginas(paginas.getTotalPages())
				.assembleiaResponses(paginas.getContent().stream()
						.map(MapperAssembleia.INSTANCE::assembleiaToResponse)
						.toList())
				.build();
	}
}
