package br.tec.db.desafio.api.v1.dto.sessao.response;

import br.tec.db.desafio.business.domain.enums.Voto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SessaoVotadaResponseV1 {
    private Voto voto;
}
