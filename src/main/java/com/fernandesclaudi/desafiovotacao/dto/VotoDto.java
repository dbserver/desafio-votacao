package com.fernandesclaudi.desafiovotacao.dto;

import com.fernandesclaudi.desafiovotacao.enums.VotoEnum;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class VotoDto {
    private Long id;
    private VotoEnum voto;
    private LocalDateTime dtVoto;
    private AssociadoDto associado;
    private SessaoDto sessao;
}
