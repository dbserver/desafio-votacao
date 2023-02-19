package br.com.dbserver.votacao.v1.mapper;

import br.com.dbserver.votacao.v1.dto.request.AssembleiaRequest;
import br.com.dbserver.votacao.v1.dto.response.AssembleiaResponse;
import br.com.dbserver.votacao.v1.entity.Assembleia;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface MapperAssembleia {
	MapperAssembleia INSTANCE = Mappers.getMapper(MapperAssembleia.class);

	Assembleia requestToAssembleia(AssembleiaRequest assembleiaRequest);
	@Mapping(target = "id", source = "id")
	AssembleiaResponse assembleiaToResponse(Assembleia assembleia);

}
