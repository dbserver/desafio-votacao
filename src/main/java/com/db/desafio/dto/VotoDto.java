package com.db.desafio.dto;


import com.db.desafio.enumerate.VotoEnum;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class VotoDto {

    @NotBlank
    private AssociadoDto associadoDto;
    @NotBlank
    private VotoEnum votoEnum;


}
