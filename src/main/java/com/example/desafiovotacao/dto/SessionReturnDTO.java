package com.example.desafiovotacao.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SessionReturnDTO {
    private Integer id;
    private String rulingTitle;
    private Integer rulingId;
    private Integer duration;
    private String creationDate;
}
