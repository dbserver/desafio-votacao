package com.db.desafio.util.factory;


import com.db.desafio.dto.PautaDto;
import com.db.desafio.entity.Pauta;

import java.util.List;

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


}
