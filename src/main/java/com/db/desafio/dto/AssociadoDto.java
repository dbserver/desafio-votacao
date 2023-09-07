package com.db.desafio.dto;


import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AssociadoDto {
    @NotBlank
    private String nome;
    @NotBlank
    private String cpf;
}
