package com.db.desafio.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SessaoVotacaoDto {

    @NotBlank
    private PautaDto pautaDto;
    private LocalDateTime inicioSessao;
    private LocalDateTime finalSessao;

}
