package com.dbserver.desafio.votacao.usecase.pauta;

import com.dbserver.desafio.votacao.usecase.domain.Pauta;

public interface IniciarPautaUsecase {

    Pauta execute(Integer idPauta, Integer duracao);
}
