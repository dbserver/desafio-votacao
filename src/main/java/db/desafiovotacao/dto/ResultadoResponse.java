package db.desafiovotacao.dto;

public record ResultadoResponse(
        Integer votosPositivos,
        Integer votosNegativos
) {}
