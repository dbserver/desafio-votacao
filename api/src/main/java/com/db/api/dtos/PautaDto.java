package com.db.api.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PautaDto {
    @NotBlank(message = "Por favor, informe o titulo da pauta.")
    private String titulo;
    private String descricao;
}
