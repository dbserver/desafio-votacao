package com.example.desafiovotacao.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ComputingVoteDTO {
    private String cpf;
    private Boolean vote;
    private Integer sessionId;
}
