package com.dbserver.desafiovotacao.dto;

import com.dbserver.desafiovotacao.enums.VotoEnum;
import jakarta.validation.constraints.NotBlank;
import lombok.NonNull;


public record VotanteRequest(@NotBlank String codAssociado, @NonNull VotoEnum votoEnum) {

}
