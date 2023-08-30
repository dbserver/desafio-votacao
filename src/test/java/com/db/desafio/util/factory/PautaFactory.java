package com.db.desafio.util.factory;


import com.db.desafio.dto.PautaDto;
import com.db.desafio.entity.Pauta;

import java.util.List;

import static com.db.desafio.util.factory.SessaoFactory.sessaoFactory2;

public class PautaFactory {
    public static List<Pauta> ListaDePautasFactory(){
        return List.of(new Pauta(1L,"titulo_1","descrição_1")
                ,new Pauta(2L,"titulo_2","descrição_2"),
                new Pauta(3L,"titulo_3","descrição_3"));
    }
    public static Pauta pautaFactory(){
        return new Pauta(1L,"titulo_1","descrição_1");
    }
    public static Pauta pautaSemIdFactory(){
        return new Pauta("titulo_1","descrição_1");
    }

    public static Pauta pautaFactory2(){
        return new Pauta(1L,"titulo_1","descrição_1",sessaoFactory2());
    }

    public static PautaDto pautaDtoFactory(){
        return new PautaDto("titulo_1","descrição_1");
    }
    public static List<PautaDto> ListaDePautasDtoFactory(){
        return List.of(new PautaDto("titulo_1","descrição_1")
                ,new PautaDto("titulo_2","descrição_2"),
                new PautaDto("titulo_3","descrição_3"));
    }


}
