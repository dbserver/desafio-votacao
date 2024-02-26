package com.fernandesclaudi.desafiovotacao.dto;

import com.fernandesclaudi.desafiovotacao.model.Associado;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDate;

@Data
public class PautaDto {
    private Long id;
    @NotEmpty(message = "O titulo deve ser informado")
    @Size(max = 50, message = "O titulo deve ter no maximo 50 caracteres")
    private String titulo;
    @NotNull(message = "O redator (Associado) deve ser informado")
    private Associado redator;
    private LocalDate data;
}
