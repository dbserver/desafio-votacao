package com.fernandesclaudi.desafiovotacao.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
public class SessaoDto {

    private Long id;
    private Long duracao;
    @NotNull(message = "Pauta não pode ser nula.")
    private PautaDto pauta;
    private LocalDateTime dataInicio;
    private LocalDateTime dataFim;

}
