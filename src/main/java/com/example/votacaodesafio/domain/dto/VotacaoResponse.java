package com.example.votacaodesafio.domain.dto;

import com.example.votacaodesafio.domain.enums.VotoEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VotacaoResponse {
    VotoEnum voto;
}
