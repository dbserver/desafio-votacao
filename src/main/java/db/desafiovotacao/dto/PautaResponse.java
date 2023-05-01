package db.desafiovotacao.dto;

import db.desafiovotacao.model.Pauta;
import java.time.format.DateTimeFormatter;

public record PautaResponse(

        Long id,

        String titulo,

        String descricao,

        String inicioSessao,

        String finalSessao

) {

    public PautaResponse(Pauta pauta){
        this(
                pauta.getId(),
                pauta.getTitulo(),
                pauta.getDescricao(),
                pauta.getSessao().getInicioSessao().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")),
                pauta.getSessao().getFinalSessao().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))
        );
    }
}
