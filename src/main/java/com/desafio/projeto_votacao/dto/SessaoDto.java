package com.desafio.projeto_votacao.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SessaoDto {

    private Long id;
    private Integer tempoSessao;
    private Boolean status;
    private PautaDto pauta;

}
