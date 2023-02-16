package br.tec.db.desafio.api.v1.dto.sessao.request;

import br.tec.db.desafio.business.domain.enums.Voto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SessaoParaVotarRequestV1 {
    public Voto voto;
    public String assuntoPauta;
}
