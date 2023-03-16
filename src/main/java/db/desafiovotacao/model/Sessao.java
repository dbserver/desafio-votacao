package db.desafiovotacao.model;

import db.desafiovotacao.dto.SessaoRequest;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Embeddable
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Sessao {

    private LocalDateTime inicioSessao;

    private LocalDateTime finalSessao;

    public Sessao(SessaoRequest sessaoRequuest){

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        this.inicioSessao = LocalDateTime.parse(sessaoRequuest.inicioSessao(), dateTimeFormatter);
        this.finalSessao = LocalDateTime.parse(sessaoRequuest.finalSessao(), dateTimeFormatter);
    }

    public void atualizar(SessaoRequest sessaoRequest){

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        if (sessaoRequest.inicioSessao() != null)
            this.inicioSessao = LocalDateTime.parse(sessaoRequest.inicioSessao(), dateTimeFormatter);

        if (sessaoRequest.finalSessao() != null)
            this.finalSessao = LocalDateTime.parse(sessaoRequest.finalSessao(), dateTimeFormatter);
    }
}

