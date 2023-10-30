package com.example.desafiovotacao.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
@AllArgsConstructor
public class CountingResultsDTO {
    private String title;
    private String description;
    private String result;
    private Long inFavorVotes;
    private Long againstVotes;
    private String creationDate;
    private String sessionDate;
    private String countDate;
}