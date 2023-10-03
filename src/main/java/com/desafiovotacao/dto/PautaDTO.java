package com.desafiovotacao.dto;

import com.desafiovotacao.domain.Pauta;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PautaDTO {

    private String id;

    private String descricao;

    public Pauta toEntity() {
        Pauta pauta = new Pauta();
        pauta.setId(this.id);
        pauta.setDescricao(this.descricao);
        return pauta;
    }

    public static PautaDTO fromEntity(Pauta pauta) {
        PautaDTO pautaDTO = new PautaDTO();
        pautaDTO.setId(pauta.getId());
        pautaDTO.setDescricao(pauta.getDescricao());
        return pautaDTO;
    }
}
