package com.db.desafio.util.factory;


import com.db.desafio.entity.Sessao;

import static com.db.desafio.util.factory.PautaFactory.pautaFactory;


public class SessaoFactory {

    public static Sessao sessaoFactory(){
        return new Sessao(pautaFactory());

    }



}
