package br.tec.db.desafio.api.v1.dto.sessao.request;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class SessaoParaCriarRequestV1 {
    public String assuntoPauta;
    public LocalDateTime duracaoSessao;
}
