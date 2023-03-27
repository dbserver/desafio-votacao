package com.dbserver.desafiovotacao.dto;

import jakarta.validation.constraints.NotBlank;

import java.util.UUID;

public record PautaRequest(@NotBlank String titulo, UUID idAutor, @NotBlank String hash) {

}
