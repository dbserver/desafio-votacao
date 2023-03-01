package com.dbserver.desafio.votacao.usecase.assembleia;

import com.dbserver.desafio.votacao.usecase.domain.VotosPauta;

public interface ContabilizarVotosUsecase {

    VotosPauta execute(Integer idPauta);
}
