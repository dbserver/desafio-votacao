package db.desafiovotacao.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record PautaRequest(
        @NotBlank @Size(min = 7, max = 200) String titulo,

        String descricao,

        @NotNull @Valid SessaoRequest sessaoRequest
) {}
