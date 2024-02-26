package com.fernandesclaudi.desafiovotacao.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.hibernate.validator.constraints.br.CPF;

@Data
public class CpfDto {
    @NotEmpty(message = "Cpf obrigatório")
    @CPF(message = "Cpf inválido")
    @Size(min = 11, max=11, message = "Cpf deve possuir 11 digitos")
    private String cpf;
}
