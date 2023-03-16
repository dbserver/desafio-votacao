package br.com.dbserver.assembleia.mapper;
import java.util.List;

import br.com.dbserver.assembleia.entidade.Pauta;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import br.com.dbserver.assembleia.dto.PautaBasicoDTO;
import br.com.dbserver.assembleia.dto.PautaCompletoDTO;

@Mapper(componentModel = "spring")
public interface PautaMapper {
 
    PautaMapper INSTANCE = Mappers.getMapper(PautaMapper.class);
 
    Pauta toPauta(PautaBasicoDTO pautaDto);

    List<PautaCompletoDTO> toPautaCompletoDtoList(List<Pauta> pautas);
    
    PautaCompletoDTO toPautaCompletoDto(Pauta pauta);
    
}