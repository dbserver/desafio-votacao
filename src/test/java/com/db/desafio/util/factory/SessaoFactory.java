package com.db.desafio.util.factory;



import com.db.desafio.entity.Sessao;

import java.time.LocalDateTime;
import java.util.List;

import static com.db.desafio.util.factory.PautaFactory.pautaFactory;


public class SessaoFactory {
    private static final LocalDateTime INICIO= LocalDateTime.parse(LocalDateTime.of(2022, 11, 23, 1, 0, 0).toString());
    private static final LocalDateTime ENCERRAMENTO = LocalDateTime.parse(LocalDateTime.of(2022, 11, 23, 1, 1, 0).toString());



    public static Sessao sessaoFactory(){
        return new Sessao(1L,pautaFactory(),INICIO, ENCERRAMENTO);

    }
    public static Sessao sessaoSemIdFactory(){
        return new Sessao(pautaFactory(),INICIO, ENCERRAMENTO);

    }

    public static List<Sessao> ListaDeSessoesFactory(){
        return List.of(new Sessao(1L,pautaFactory(),INICIO,ENCERRAMENTO)
                ,new Sessao(2L,pautaFactory(),INICIO,ENCERRAMENTO));
    }



}
