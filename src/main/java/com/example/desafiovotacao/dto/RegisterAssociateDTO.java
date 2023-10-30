package com.example.desafiovotacao.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterAssociateDTO {
    private String cpf;
    private String name;
}
