package com.dbserver.desafiovotacao.dto;

import com.dbserver.desafiovotacao.enums.PautaResultadoEnum;
import com.dbserver.desafiovotacao.model.Pauta;

import java.util.UUID;

public record PautaResponse(UUID id, String titulo, PautaResultadoEnum resultadoEnum) {
    public PautaResponse(Pauta pauta){
        this(pauta.getId(), pauta.getTitulo(), pauta.getResultado());
    }
}
