package br.com.dbserver.voting.dtos;

import jakarta.validation.constraints.NotBlank;

import java.io.Serializable;

public record AssociateDTO(
        Integer id,
        @NotBlank(message = "Nome do associado nao pode estar em branco") String name,
        @NotBlank(message = "CPF do associado nao pode estar em branco") String cpf
) implements Serializable {
}
