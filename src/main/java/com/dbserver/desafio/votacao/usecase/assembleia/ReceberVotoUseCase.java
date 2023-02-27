package com.dbserver.desafio.votacao.usecase.assembleia;

import com.dbserver.desafio.votacao.exception.SessaoFinalizadaException;
import com.dbserver.desafio.votacao.exception.VotoJaRealizadoException;
import com.dbserver.desafio.votacao.usecase.domain.Voto;

public interface ReceberVotoUseCase {

    Voto execute(Voto voto) throws SessaoFinalizadaException, VotoJaRealizadoException;
}
