package com.example.votacaodesafio.domain.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AssosciadoDTO {

    private Long id;
    private String nome;
    private String cpf;
    private LocalDateTime data;

}
