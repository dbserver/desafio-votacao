package br.com.dbserver.voting.dtos;

import jakarta.validation.constraints.NotBlank;

import java.util.UUID;

public record AssociateDTO(
        UUID id,
        @NotBlank(message = "Nome do associado nao pode estar em branco") String name,
        @NotBlank(message = "CPF do associado nao pode estar em branco") String cpf
) {
}
