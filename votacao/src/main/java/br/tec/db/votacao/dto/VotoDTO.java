package br.tec.db.votacao.dto;

import br.tec.db.votacao.enums.VotoStatusEnum;
import br.tec.db.votacao.model.Voto;
import jakarta.validation.constraints.NotBlank;

public record VotoDTO(@NotBlank VotoStatusEnum voto, @NotBlank Long idSessaoDeVotacao, @NotBlank Long idAssociado) {

    public VotoDTO(Voto voto) {
        this(voto.getStatus(), voto.getSessaoDeVotacao().getId(), voto.getAssociado().getId());
    }
}