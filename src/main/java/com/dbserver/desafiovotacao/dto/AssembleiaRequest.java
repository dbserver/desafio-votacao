package com.dbserver.desafiovotacao.dto;

import jakarta.validation.constraints.NotBlank;

public record AssembleiaRequest(@NotBlank String nomeAssembleia) {


}
