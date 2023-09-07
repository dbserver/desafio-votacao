package com.db.desafio.util.factory;


import com.db.desafio.dto.VotoDto;
import com.db.desafio.entity.Voto;
import com.db.desafio.enumerate.VotoEnum;

import java.util.ArrayList;
import java.util.List;

import static com.db.desafio.util.factory.AssociadoFactory.associadoFactory;
import static com.db.desafio.util.factory.AssociadoFactory.associadoFactory2;


public class VotoFactory {


    public static VotoDto votoDtoFactory(){
        return new VotoDto("256.154.452-22",VotoEnum.SIM);

    }

    public static List<Voto> ListaDeVotosEnumFactory(){
        return List.of(new Voto(VotoEnum.SIM), new Voto(VotoEnum.SIM),new Voto(VotoEnum.SIM));

    }
    public static List<Voto> ListaDeVotosFactory(){
        List<Voto> votos = new ArrayList<>();
        votos.add(new Voto(VotoEnum.SIM,associadoFactory()));
        votos.add(new Voto(VotoEnum.SIM,associadoFactory2()));
        return votos;

    }
}
