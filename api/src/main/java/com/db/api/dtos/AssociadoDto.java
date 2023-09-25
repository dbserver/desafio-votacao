package com.db.api.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AssociadoDto {
    @NotBlank(message = "Por favor informe o nome do associado!")
    @Size(min = 3, max = 150)
    private String nome;
    @NotBlank(message = "Por favor informe o cpf do associado!")
    @CPF
    private String cpf;
}
