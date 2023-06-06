package com.example.votacaodesafio.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class VotacaoResultadoResponse {

    Long sim;
    Long nao;
    Long total;
}
