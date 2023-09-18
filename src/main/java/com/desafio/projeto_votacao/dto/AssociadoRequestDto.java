package com.desafio.projeto_votacao.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AssociadoRequestDto {

    @NotBlank(message = "O campo nome não deve ser vazio.")
    private String nome;
    @NotBlank(message = "O campo cpf não deve ser vazio.")
    @CPF(message = "Cpf não é válido.")
    private String cpf;
}
