package com.db.desafio.dto;


import com.db.desafio.enumerate.VotoEnum;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class VotoDto {

    @NotBlank
    private String cpf;
    @NotBlank
    private VotoEnum votoEnum;


}
