package com.dbserver.desafiovotacao.dto;

import com.dbserver.desafiovotacao.enums.AssembleiaEnum;
import com.dbserver.desafiovotacao.model.Assembleia;

import java.time.LocalDateTime;
import java.util.UUID;

public record AssembleiaResponse (UUID idAssembleia, LocalDateTime abertura, LocalDateTime fechamento, AssembleiaEnum status) {

    public AssembleiaResponse(Assembleia assembleia){
        this(assembleia.getId(),assembleia.getAberturaAssembleia(),assembleia.getEncerramentoAssembleia(),assembleia.getStatus());
    }
}
