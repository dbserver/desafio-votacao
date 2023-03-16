package db.desafiovotacao.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.br.CPF;

public record VotoPautaRequest(
        @NotBlank @CPF String cpf,
        @NotNull Long idPauta,
        @NotNull @Valid VotoRequest voto
) {}
