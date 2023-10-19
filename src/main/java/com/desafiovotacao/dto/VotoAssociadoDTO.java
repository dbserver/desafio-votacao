package com.desafiovotacao.dto;

import com.desafiovotacao.domain.VotoAssociado;
import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class VotoAssociadoDTO {

    private String id;

    @NotNull
    private LocalDateTime data;

    @NotNull
    private TipoVotoEnum tipo;

    @NotNull
    private String associadoId;

    @NotNull
    private String sessaoId;

    @NotNull
    private String pautaId;

    public VotoAssociado toEntity() {
        VotoAssociado votoAssociado = new VotoAssociado();
        votoAssociado.setId(this.id);
        votoAssociado.setData(this.data);
        votoAssociado.setTipo(this.tipo);
        votoAssociado.setPautaId(this.pautaId);
        return votoAssociado;
    }

    public static VotoAssociadoDTO fromEntity(VotoAssociado votoAssociado) {
        VotoAssociadoDTO votoAssociadoDTO = new VotoAssociadoDTO();
        votoAssociadoDTO.setId(votoAssociado.getId());
        votoAssociadoDTO.setData(votoAssociado.getData());
        votoAssociadoDTO.setTipo(votoAssociado.getTipo());
        votoAssociadoDTO.setAssociadoId(votoAssociado.getAssociado().getId());
        votoAssociadoDTO.setSessaoId(votoAssociado.getSessaoPauta().getId());
        votoAssociadoDTO.setPautaId(votoAssociado.getPautaId());
        return votoAssociadoDTO;
    }
}
