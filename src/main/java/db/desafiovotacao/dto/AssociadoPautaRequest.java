package db.desafiovotacao.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.br.CPF;

public record AssociadoPautaRequest (
        @NotNull Long idPauta,

        @NotBlank @CPF String cpf
) {}
