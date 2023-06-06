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
public class PautaDTO {

    private Long id;
    private String titulo;
    private String descricao;
    private List<SessaoVotacaoDTO> sessoesVotacao = new ArrayList<>();
    private LocalDateTime data;
}
