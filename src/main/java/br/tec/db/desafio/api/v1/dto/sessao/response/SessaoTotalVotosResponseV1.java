package br.tec.db.desafio.api.v1.dto.sessao.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SessaoTotalVotosResponseV1 {
    private int totalVotosSim;
    private int totalVotosNao;
    private Boolean sessaoEncerrada;
}
