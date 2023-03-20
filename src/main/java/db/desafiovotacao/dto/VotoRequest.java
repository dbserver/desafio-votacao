package db.desafiovotacao.dto;

import db.desafiovotacao.enums.EnumVoto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record VotoRequest(
        @NotNull(message = "voto não pode ser nulo")
        EnumVoto voto,

        @NotBlank(message = "data não pode estar em branco")
        String dataHoraVoto
) {}
