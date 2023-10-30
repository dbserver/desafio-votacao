package com.example.desafiovotacao.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
@AllArgsConstructor
public class CreatedRulingDTO {
    private Integer rulingId;
    private String title;
    private String description;
}
