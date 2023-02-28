package com.dbserver.desafio.valida.cpf.usecase.assembleia;

import com.dbserver.desafio.valida.cpf.usecase.domain.VotosPauta;

public interface ContabilizarVotosUsecase {

    VotosPauta execute(Integer idPauta);
}
