package com.db.desafio.util.factory;


import com.db.desafio.dto.PautaResultadoDto;

public class PautaResultadoDtoFactory {

    public static PautaResultadoDto pautaResultadoDtoFactory(){
        return new PautaResultadoDto("titulo_1","Aprovado");
    }



}
