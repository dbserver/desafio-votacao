package br.tec.db.desafio.api.v1.dto.sessao.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SessaoParaSaberTotalVotosRequestV1 {
    public String assuntoPauta;
}
