package db.desafiovotacao.dto;

import db.desafiovotacao.model.Pauta;

public record PautaResponse(

        String id,
        String titulo,

        String inicioSessao,

        String finalSessao

) {

    public PautaResponse(Pauta pauta){
        this(
                pauta.getId().toString(),
                pauta.getTitulo(),
                pauta.getSessao().getInicioSessao().toString(),
                pauta.getSessao().getFinalSessao().toString()
        );
    }
}
