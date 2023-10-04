package com.desafiovotacao.dto;

import com.desafiovotacao.domain.Pauta;
import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PautaDTO {

    private String id;

    @NotNull
    private String descricao;

    private Long votosAFavor;

    private Long votosContra;

    public Pauta toEntity() {
        Pauta pauta = new Pauta();
        pauta.setId(this.id);
        pauta.setDescricao(this.descricao);
        pauta.setVotosContra(this.votosContra);
        pauta.setVotosAFavor(this.votosAFavor);
        return pauta;
    }

    public static PautaDTO fromEntity(Pauta pauta) {
        PautaDTO pautaDTO = new PautaDTO();
        pautaDTO.setId(pauta.getId());
        pautaDTO.setDescricao(pauta.getDescricao());
        pautaDTO.setVotosContra(pauta.getVotosContra());
        pautaDTO.setVotosAFavor(pautaDTO.getVotosAFavor());
        return pautaDTO;
    }
}
