package db.desafiovotacao.dto;

import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.br.CPF;

public record AssociadoRequest(
        @NotBlank @CPF String cpf
) {}
