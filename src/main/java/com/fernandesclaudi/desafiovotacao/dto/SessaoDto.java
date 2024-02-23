package com.fernandesclaudi.desafiovotacao.dto;

import com.fernandesclaudi.desafiovotacao.model.Pauta;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
public class SessaoDto {

    private Long id;
    private Long duracao;
    private PautaDto pauta;
    private LocalDateTime dataInicio;
    private LocalDateTime dataFim;

}
