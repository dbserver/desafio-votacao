package com.example.votacaodesafio.domain.dto;

import com.example.votacaodesafio.domain.enums.VotoEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VotacaoDTO {

    private Long id;
    private SessaoVotacaoDTO sessaoVotacao;
    private AssosciadoDTO assosciado;
    private VotoEnum vote;
    private LocalDateTime criacao;
}
