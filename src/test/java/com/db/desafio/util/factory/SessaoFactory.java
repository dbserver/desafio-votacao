package com.db.desafio.util.factory;


import com.db.desafio.entity.Sessao;

import java.time.LocalDateTime;

import static com.db.desafio.util.factory.PautaFactory.pautaFactory;


public class SessaoFactory {
    private static final LocalDateTime INICIO= LocalDateTime.parse(LocalDateTime.of(2022, 11, 23, 1, 0, 0).toString());
    private static final LocalDateTime ENCERRAMENTO = LocalDateTime.parse(LocalDateTime.of(2022, 11, 23, 1, 1, 0).toString());



    public static Sessao sessaoFactory(){
        return new Sessao(1L,pautaFactory(),INICIO, ENCERRAMENTO);

    }



}
