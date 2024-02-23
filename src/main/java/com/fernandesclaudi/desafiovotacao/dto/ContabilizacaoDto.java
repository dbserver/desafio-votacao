package com.fernandesclaudi.desafiovotacao.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ContabilizacaoDto {
    private Integer countVotosSim;
    private Integer countVotosNao;
    private Integer countVotos;
}
