package com.dbserver.desafio.votacao.usecase.assembleia;

import com.dbserver.desafio.votacao.usecase.domain.Voto;

public interface ReceberVotoUseCase {

    Voto execute(Voto voto);
}
