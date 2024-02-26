package com.fernandesclaudi.desafiovotacao.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class VotoDto {
    private Long id;
    @Pattern(regexp = "^([SN])$", message = "Valores esperados para o voto: S (Sim) ou N (Não)")
    private String voto;
    private LocalDateTime dtVoto;
    @NotNull(message = "Associado deve ser informado")
    private AssociadoDto associado;
    @NotNull(message = "Sessão deve ser informada")
    private SessaoDto sessao;
}
