package db.desafiovotacao.dto;

import db.desafiovotacao.enums.EnumVoto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record VotoRequest(
        @NotNull EnumVoto voto,

        @NotBlank String dataHoraVoto
) {}
