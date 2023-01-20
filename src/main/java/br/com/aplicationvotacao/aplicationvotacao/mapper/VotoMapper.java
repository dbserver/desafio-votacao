package br.com.aplicationvotacao.aplicationvotacao.mapper;


import br.com.aplicationvotacao.aplicationvotacao.dto.VotoDTO;
import br.com.aplicationvotacao.aplicationvotacao.model.SessaoVotacao;
import br.com.aplicationvotacao.aplicationvotacao.model.Voto;
import br.com.aplicationvotacao.aplicationvotacao.model.enums.VotoEnum;

public class VotoMapper {

    public static Voto dtoParaVoto(VotoDTO votoDTO, SessaoVotacao sessaoVotacao){

        return Voto.builder()
                .idAssociado(votoDTO.getIdAssociado())
                .sessaoVotacao(sessaoVotacao)
                .voto(votoDTO.isVoto() ? VotoEnum.SIM : VotoEnum.NAO)
                .build();
    }

    public static VotoDTO votoParaDTO(Voto voto){

        return VotoDTO.builder()
                .idAssociado(voto.getIdAssociado())
                .id_sessao_votacao(voto.getSessaoVotacao().getId())
                .voto(voto.getVoto().equals(VotoEnum.SIM))
                .build();
    }
}
