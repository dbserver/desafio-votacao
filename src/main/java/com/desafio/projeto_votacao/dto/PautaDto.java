package com.desafio.projeto_votacao.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PautaDto {

    private Long id;
    private String titulo;
    private String descricao;
}
