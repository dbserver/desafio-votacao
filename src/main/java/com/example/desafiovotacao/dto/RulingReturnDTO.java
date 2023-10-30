package com.example.desafiovotacao.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
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