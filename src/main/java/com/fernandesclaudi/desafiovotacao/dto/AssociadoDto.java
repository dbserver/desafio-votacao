package com.fernandesclaudi.desafiovotacao.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import org.hibernate.validator.constraints.br.CPF;

@Data
public class AssociadoDto {

    private Long id;
    @NotEmpty(message = "Nome não pode ser vazio.")
    private String nome;
    @CPF(message = "Cpf inválido.")
    @NotEmpty(message = "Cpf não pode ser vazio.")
    private String cpf;
}
