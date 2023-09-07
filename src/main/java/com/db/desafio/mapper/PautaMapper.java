package com.db.desafio.mapper;


import com.db.desafio.dto.PautaDto;
import com.db.desafio.entity.Pauta;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface PautaMapper {
    PautaMapper INSTANCE = Mappers.getMapper(PautaMapper.class);

    Pauta pautDtoParaPauta(PautaDto pautaDto);
    PautaDto pautaParaPautaDto(Pauta pauta);
    List<PautaDto> listaPautaParaListaPautaDto(List<Pauta> pautas);
}
