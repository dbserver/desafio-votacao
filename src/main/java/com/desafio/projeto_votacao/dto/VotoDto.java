package com.desafio.projeto_votacao.dto;

import com.desafio.projeto_votacao.enums.VotoEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class VotoDto {

    private Long id;
    private VotoEnum votoEnum;
    private AssociadoDto associado;
    private PautaDto pauta;
}
