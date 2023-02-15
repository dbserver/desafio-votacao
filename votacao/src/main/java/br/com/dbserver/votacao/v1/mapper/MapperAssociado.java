package br.com.dbserver.votacao.v1.mapper;

import br.com.dbserver.votacao.v1.dto.request.AssociadoRequest;
import br.com.dbserver.votacao.v1.dto.response.AssociadoResponse;
import br.com.dbserver.votacao.v1.entity.Associado;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;


@Mapper
public interface MapperAssociado {

    MapperAssociado INSTANCE = Mappers.getMapper( MapperAssociado.class );

    AssociadoResponse associadoToResponse(Associado associadoRequest);
    Associado associadoRequesToAssociado(AssociadoRequest associadoRequest);
}
