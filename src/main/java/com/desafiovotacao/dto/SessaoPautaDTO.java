package com.desafiovotacao.dto;

import com.desafiovotacao.domain.SessaoPauta;
import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class SessaoPautaDTO {

    private String id;

    private LocalDateTime dataInicio;

    private LocalDateTime dataFim;

    private long duracaoMinutos;

    @NotNull
    private String pautaId;

    public SessaoPauta toEntity() {
        SessaoPauta sessaoPauta = new SessaoPauta();
        sessaoPauta.setId(this.id);
        sessaoPauta.setDataInicio(this.dataInicio);
        sessaoPauta.setDataFim(this.dataFim);
        sessaoPauta.setDuracaoMinutos(this.duracaoMinutos);
        return sessaoPauta;
    }

    public static SessaoPautaDTO fromEntity(SessaoPauta sessaoPauta) {
        SessaoPautaDTO sessaoPautaDTO = new SessaoPautaDTO();
        sessaoPautaDTO.setId(sessaoPauta.getId());
        sessaoPautaDTO.setDataInicio(sessaoPauta.getDataInicio());
        sessaoPautaDTO.setDataFim(sessaoPauta.getDataFim());
        sessaoPautaDTO.setPautaId(sessaoPauta.getPauta().getId());
        sessaoPautaDTO.setDuracaoMinutos(sessaoPautaDTO.duracaoMinutos);
        return sessaoPautaDTO;
    }
}
