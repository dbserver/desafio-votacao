package br.tec.db.votacao.dto;

import br.tec.db.votacao.model.SessaoDeVotacao;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDateTime;

public record SessaoDeVotacaoDTO(@NotBlank LocalDateTime inicio, Long idPauta) {

    public SessaoDeVotacaoDTO(SessaoDeVotacao sessaoDeVotacao) {
        this(sessaoDeVotacao.getInicio(), sessaoDeVotacao.getPauta().getId());
    }
}


