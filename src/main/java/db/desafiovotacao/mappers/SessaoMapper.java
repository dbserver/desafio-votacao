package db.desafiovotacao.mappers;

import db.desafiovotacao.dto.SessaoRequest;
import db.desafiovotacao.model.Sessao;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class SessaoMapper {

    public static Sessao mapearSessao(SessaoRequest sessaoRequest){

        Sessao sessao = new Sessao();

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        sessao.setInicioSessao(LocalDateTime.parse(sessaoRequest.inicioSessao(), dateTimeFormatter));
        sessao.setFinalSessao(LocalDateTime.parse(sessaoRequest.finalSessao(), dateTimeFormatter));

        return sessao;
    }
}
