package db.desafiovotacao.dto;

import db.desafiovotacao.model.Resultado;

public record ResultadoResponse(
        Integer votosPositivos,
        Integer votosNegativos
) {
    public ResultadoResponse(Resultado resultado){
        this(resultado.getVotosPositivos(), resultado.getVotosNegativos());
    }
}
