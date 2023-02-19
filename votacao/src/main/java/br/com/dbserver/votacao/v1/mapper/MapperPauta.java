package br.com.dbserver.votacao.v1.mapper;

import br.com.dbserver.votacao.v1.dto.request.PautaRequest;
import br.com.dbserver.votacao.v1.dto.response.PautaResponse;
import br.com.dbserver.votacao.v1.dto.response.PautaResultadoResponse;
import br.com.dbserver.votacao.v1.entity.Pauta;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface MapperPauta {

	MapperPauta INSTANCE = Mappers.getMapper(MapperPauta.class);

	Pauta requestToPauta(PautaRequest pautaRequest);
	@Mapping(target = "id", source = "id")
	PautaResponse pautaToResponse(Pauta pauta);

	@Mapping(target = "id", source = "id")
	PautaResultadoResponse pautaToPautaResultadoResponse(Pauta pauta);
}
