package db.desafiovotacao.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public record PautaAtualizarRequest(
        @NotNull Long id,
        String titulo,
        String descricao,
        @NotNull @Valid SessaoRequest sessaoRequest
) {}
