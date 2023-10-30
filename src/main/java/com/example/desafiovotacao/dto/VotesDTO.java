package com.example.desafiovotacao.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VotesDTO {
    private Long votesInFavor;
    private Long votesAgainst;
}