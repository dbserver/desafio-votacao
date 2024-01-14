package br.com.dbserver.voting.dtos.vote;

import jakarta.validation.constraints.NotBlank;

import java.io.Serializable;

public record VoteRequestDTO(@NotBlank(message = "Id da sessao de votacao nao pode estar em branco")  String idSessionVoting,
                             @NotBlank(message = "CPF nao pode estar em branco") String cpf,
                             @NotBlank(message = "Voto nao pode estar em branco") String vote) implements Serializable {
}
