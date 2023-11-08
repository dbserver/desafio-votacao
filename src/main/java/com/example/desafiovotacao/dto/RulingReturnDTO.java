package com.example.desafiovotacao.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RulingReturnDTO {
    private Integer id;
    private String title;
    private String description;
    private String result;
    private String creationDate;
    private String resultDate;
}