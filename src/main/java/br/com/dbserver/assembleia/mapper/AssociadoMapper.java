package br.com.dbserver.assembleia.mapper;
import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import br.com.dbserver.assembleia.dto.AssociadoBasicoDTO;
import br.com.dbserver.assembleia.dto.AssociadoCompletoDTO;
import br.com.dbserver.assembleia.entidade.Associado;
 
@Mapper(componentModel = "spring")
public interface AssociadoMapper {
 
    AssociadoMapper INSTANCE = Mappers.getMapper(AssociadoMapper.class);
 
    Associado toAssociado(AssociadoBasicoDTO associadoBasicoDTO);

    List<AssociadoCompletoDTO> toAssociadoCompletoDtoList(List<Associado> associados);
    
    AssociadoCompletoDTO toAssociadoCompletoDto(Associado associado);

    
}