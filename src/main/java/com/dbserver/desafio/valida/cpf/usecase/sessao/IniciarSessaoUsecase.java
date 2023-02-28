package com.dbserver.desafio.valida.cpf.usecase.sessao;

import com.dbserver.desafio.valida.cpf.usecase.domain.Sessao;

public interface IniciarSessaoUsecase {

    Sessao execute(Integer duracao);
}
