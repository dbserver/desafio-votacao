package com.dbserver.desafio.valida.cpf.usecase.pauta;

import com.dbserver.desafio.valida.cpf.usecase.domain.Pauta;

public interface IniciarPautaUsecase {

    Pauta execute(Integer idPauta, Integer duracao);
}
