package br.tec.db.votacao.dto;

import br.tec.db.votacao.enums.AssembleiaStatusEnum;
import br.tec.db.votacao.model.Assembleia;

import java.time.LocalDateTime;

public record AssembleiaDTO(LocalDateTime inicio, LocalDateTime fim, AssembleiaStatusEnum status) {

    public AssembleiaDTO(Assembleia assembleia) {
        this(assembleia.getInicio(), assembleia.getFim(), assembleia.getStatus());
    }
}