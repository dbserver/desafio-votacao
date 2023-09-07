package com.db.desafio.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@NoArgsConstructor
@Getter
@Setter
public class PautaDto {

    @NotBlank
    private String titulo;
    @NotBlank
    private String descricao;

    public PautaDto(String titulo, String descricao) {
        this.titulo = titulo;
        this.descricao = descricao;
    }
}
