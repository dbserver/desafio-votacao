package br.com.dbserver.votacao.v1.mapper;

import br.com.dbserver.votacao.v1.dto.response.PautaVotoResponse;
import br.com.dbserver.votacao.v1.dto.response.VotoResponse;
import br.com.dbserver.votacao.v1.entity.Voto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface MapperVoto {

	MapperVoto INSTANCE = Mappers.getMapper(MapperVoto.class);
	@Mapping(target = "id", source = "id")
	VotoResponse votoToResponse(Voto voto);
	@Mapping(target = "id", source = "id")
	PautaVotoResponse votoToPautaVotoResponse(Voto voto);
}
