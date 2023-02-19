package br.com.dbserver.votacao.v1.mapper;

import br.com.dbserver.votacao.v1.dto.request.AssociadoRequest;
import br.com.dbserver.votacao.v1.dto.response.AssociadoResponse;
import br.com.dbserver.votacao.v1.entity.Associado;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;


@Mapper
public interface MapperAssociado {

    MapperAssociado INSTANCE = Mappers.getMapper( MapperAssociado.class );
    @Mapping(target = "id", source = "id")
    AssociadoResponse associadoToResponse(Associado associado);

    Associado associadoRequesToAssociado(AssociadoRequest associadoRequest);
}
