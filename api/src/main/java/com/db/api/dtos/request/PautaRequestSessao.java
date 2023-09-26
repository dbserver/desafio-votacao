package com.db.api.dtos.request;

import lombok.Getter;

import javax.validation.constraints.NotBlank;

@Getter
public class PautaRequestSessao {
    @NotBlank(message = "Por favor, informe o titulo da pauta.")
    private String titulo;
}
