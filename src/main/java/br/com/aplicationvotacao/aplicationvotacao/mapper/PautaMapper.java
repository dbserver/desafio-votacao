package br.com.aplicationvotacao.aplicationvotacao.mapper;


import br.com.aplicationvotacao.aplicationvotacao.dto.PautaDTO;
import br.com.aplicationvotacao.aplicationvotacao.model.Pauta;

public class PautaMapper {

    public static Pauta dtoParaPauta(PautaDTO pautaDTO){
        return Pauta.builder()
                .descricao(pautaDTO.getDescricao())
                .build();
    }

    public static PautaDTO pautaParaDTO(Pauta pauta){
        return PautaDTO.builder()
                .descricao(pauta.getDescricao())
                .build();
    }
}
