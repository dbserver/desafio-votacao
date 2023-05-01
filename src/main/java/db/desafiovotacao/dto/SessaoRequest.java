package db.desafiovotacao.dto;


import db.desafiovotacao.model.Sessao;
import jakarta.validation.constraints.NotBlank;

import java.time.format.DateTimeFormatter;

public record SessaoRequest(

        @NotBlank(message = "deve ser informada uma data de inicio")
        String inicioSessao,

        @NotBlank(message = "deve ser informada uma data de fim")
        String finalSessao
){
        private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        public SessaoRequest(Sessao sessao){
                this(sessao.getInicioSessao().format(dateTimeFormatter), sessao.getFinalSessao().format(dateTimeFormatter));
        }
}
