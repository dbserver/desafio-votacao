package com.db.desafio.mapper;


import com.db.desafio.dto.AssociadoDto;
import com.db.desafio.entity.Associado;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface AssociadoMapper {
    AssociadoMapper INSTANCE = Mappers.getMapper(AssociadoMapper.class);

    Associado associadoDtoParaAssociado(AssociadoDto associadoDto);
    AssociadoDto associadoParaAssociadoDto(Associado associado);
    List<AssociadoDto> listaAssociadoParaListaAssociadoDto(List<Associado> associados);
}
