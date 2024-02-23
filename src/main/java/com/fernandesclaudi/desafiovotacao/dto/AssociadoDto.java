package com.fernandesclaudi.desafiovotacao.dto;

import lombok.Data;
import org.jetbrains.annotations.NotNull;

@Data
public class AssociadoDto {
    private Long id;
    private String nome;
    private String cpf;
}
