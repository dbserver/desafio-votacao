package br.tec.db.desafio.api.v1.dto.sessao.request;

import br.tec.db.desafio.business.domain.enums.Voto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SessaoParaVotarRequestV1 {
    public Voto voto;
    public String cpf;
    public String assuntoPauta;
}
