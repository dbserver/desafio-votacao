package com.db.desafio.util.factory;



import com.db.desafio.entity.SessaoVotacao;

import java.time.LocalDateTime;
import java.util.List;

import static com.db.desafio.util.factory.PautaFactory.pautaFactory;
import static com.db.desafio.util.factory.VotoFactory.ListaDeVotosEnumFactory;
import static com.db.desafio.util.factory.VotoFactory.ListaDeVotosFactory;


public class SessaoFactory {
    private static final LocalDateTime INICIO= LocalDateTime.parse(LocalDateTime.of(2022, 11, 23, 1, 0, 0).toString());
    private static final LocalDateTime ENCERRAMENTO = LocalDateTime.parse(LocalDateTime.of(2022, 11, 23, 1, 1, 0).toString());



    public static SessaoVotacao sessaoFactory(){
        return new SessaoVotacao(1L,pautaFactory(),INICIO, ENCERRAMENTO);

    }
    public static SessaoVotacao sessaoSemIdFactory(){
        return new SessaoVotacao(pautaFactory(),INICIO, ENCERRAMENTO);

    }

    public static List<SessaoVotacao> ListaDeSessoesFactory(){
        return List.of(new SessaoVotacao(1L,pautaFactory(),INICIO,ENCERRAMENTO)
                ,new SessaoVotacao(2L,pautaFactory(),INICIO,ENCERRAMENTO));
    }
    public static SessaoVotacao sessaoFactory2(){
        return new SessaoVotacao(1L,INICIO, ENCERRAMENTO,ListaDeVotosEnumFactory());

    }
    public static SessaoVotacao sessaoFactory3(){
        return new SessaoVotacao(1L,pautaFactory(),INICIO, ENCERRAMENTO,ListaDeVotosEnumFactory());

    }
    public static SessaoVotacao sessaoFactory4(){
        return new SessaoVotacao(1L,pautaFactory(),INICIO, LocalDateTime.now().plusMinutes(1),ListaDeVotosFactory());

    }




}
