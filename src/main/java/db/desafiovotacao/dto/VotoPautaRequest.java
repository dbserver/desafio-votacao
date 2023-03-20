package db.desafiovotacao.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.br.CPF;

public record VotoPautaRequest(
        @NotBlank(message = "cpf não pode estar em branco")
        @CPF(message = "cpf invalido")
        String cpf,
        @NotNull(message = "deve ser informada uma pauta")
        Long idPauta,
        @NotNull @Valid VotoRequest voto
) {}
