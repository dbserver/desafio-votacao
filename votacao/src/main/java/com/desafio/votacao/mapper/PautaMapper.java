package com.desafio.votacao.mapper;

import java.util.List;

import com.desafio.votacao.entity.Pauta;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.desafio.votacao.dto.PautaBasicoDTO;
import com.desafio.votacao.dto.PautaCompletoDTO;

@Mapper(componentModel = "spring")
public interface PautaMapper {

    PautaMapper INSTANCE = Mappers.getMapper(PautaMapper.class);

    Pauta toPauta(PautaBasicoDTO pautaDto);

    List<PautaCompletoDTO> toPautaCompletoDtoList(List<Pauta> pautas);

    PautaCompletoDTO toPautaCompletoDto(Pauta pauta);
    
}
