package com.example.desafiovotacao.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
@AllArgsConstructor
public class CreatedSessionDTO {
    private Integer sessionId;
    private String creationDate;
    private Integer duration;
}
