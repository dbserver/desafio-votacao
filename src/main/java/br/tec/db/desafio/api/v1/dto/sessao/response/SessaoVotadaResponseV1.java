package br.tec.db.desafio.api.v1.dto.sessao.response;

import br.tec.db.desafio.business.domain.enums.Voto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SessaoVotadaResponseV1 {
    private Voto voto;
}
