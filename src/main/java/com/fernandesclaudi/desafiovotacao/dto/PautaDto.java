package com.fernandesclaudi.desafiovotacao.dto;

import com.fernandesclaudi.desafiovotacao.model.Associado;
import lombok.Data;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.time.LocalDate;

@Data
public class PautaDto {
    private Long id;
    @Nullable
    private String titulo;
    private Associado redator;
    private LocalDate data;
}
