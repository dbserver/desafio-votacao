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
public class VotoRequestDto {

    @NotBlank(message = "O campo cpfAssociado não pode ser vazio.")
    @CPF(message = "Cpf não é válido.")
    private String cpfAssociado;

}
