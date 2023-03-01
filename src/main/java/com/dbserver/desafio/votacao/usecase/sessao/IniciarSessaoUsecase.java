package com.dbserver.desafio.votacao.usecase.sessao;

import com.dbserver.desafio.votacao.usecase.domain.Sessao;

public interface IniciarSessaoUsecase {

    Sessao execute(Integer duracao);
}
