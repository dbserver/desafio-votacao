package br.tec.db.votacao.dto;

import br.tec.db.votacao.model.Assembleia;

import java.time.LocalDateTime;

public record AssembleiaDTO(LocalDateTime inicio) {

    public AssembleiaDTO(Assembleia assembleia) {
        this(assembleia.getInicio());
    }
}