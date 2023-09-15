package com.desafio.projeto_votacao.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResultadoVotacaoDto {

    private Long totalVotos = 0L;
    private Long votosSim = 0L;
    private Long votosNao = 0L;
    private String aprovado = "Votação não iniciada.";

}
