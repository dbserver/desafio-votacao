package com.db.desafio.dto;


import com.db.desafio.enumerate.VotoEnum;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class VotoDto {

    @NotBlank
    private String cpf;
    @NotNull
    private VotoEnum votoEnum;


}
