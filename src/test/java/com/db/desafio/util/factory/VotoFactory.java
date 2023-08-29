package com.db.desafio.util.factory;


import com.db.desafio.dto.VotoDto;
import com.db.desafio.entity.Voto;
import com.db.desafio.enumerate.VotoEnum;

import static com.db.desafio.util.factory.AssociadoFactory.associadoFactory;
import static com.db.desafio.util.factory.PautaFactory.pautaFactory;


public class VotoFactory {


    public static Voto votoFactory(){
        return new Voto(VotoEnum.SIM,pautaFactory(),associadoFactory());

    }
    public static VotoDto votoDtoFactory(){
        return new VotoDto("256.154.452-22",VotoEnum.SIM);

    }



}
