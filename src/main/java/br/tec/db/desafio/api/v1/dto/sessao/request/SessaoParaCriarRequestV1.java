package br.tec.db.desafio.api.v1.dto.sessao.request;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;

import java.time.LocalDateTime;

@Getter
@Setter
public class SessaoParaCriarRequestV1 {
    public String assuntoPauta;

    public Long duracaoSessaoEmMinuto=1L;
}
