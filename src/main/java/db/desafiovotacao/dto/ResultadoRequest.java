package db.desafiovotacao.dto;

import jakarta.validation.constraints.NotNull;

public record ResultadoRequest(
        @NotNull Long idPauta
) {}
