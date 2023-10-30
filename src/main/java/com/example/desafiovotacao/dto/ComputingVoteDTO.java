package com.example.desafiovotacao.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ComputingVoteDTO {
    private String cpf;
    private Boolean vote;
    private Integer sessionId;
}
