package com.example.votacaodesafio.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SessaoVotacaoDTO {

    private Long id;
    private PautaDTO pauta;
    private LocalDateTime data;
    private LocalDateTime abertura;
    private LocalDateTime fechamento;
    private List<VotacaoDTO> listaVotos = new ArrayList<>();

}
