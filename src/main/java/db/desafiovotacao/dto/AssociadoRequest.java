package db.desafiovotacao.dto;

import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.br.CPF;

public record AssociadoRequest(
        @NotBlank(message = "cpf não pode estar em branco")
        @CPF(message = "cpf invalido")
        String cpf
) {}
