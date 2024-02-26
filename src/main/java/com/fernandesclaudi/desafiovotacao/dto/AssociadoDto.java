package com.fernandesclaudi.desafiovotacao.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class AssociadoDto {

    private Long id;
    @NotEmpty(message = "Nome não pode ser vazio.")
    private String nome;
    @NotEmpty(message = "Cpf não pode ser vazio.")
    private String cpf;
}
