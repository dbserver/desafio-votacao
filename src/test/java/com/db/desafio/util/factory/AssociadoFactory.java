package com.db.desafio.util.factory;



import com.db.desafio.dto.AssociadoDto;
import com.db.desafio.entity.Associado;

import java.util.List;

public class AssociadoFactory {
    public static List<Associado> ListaDeAssociadosFactory(){
        return List.of(new Associado(1L,"Joao","256.525")
                ,new Associado(2L,"Maria","6665.554"),
                new Associado(3L,"Joaquim","5485.98441"));
    }
    public static Associado associadoFactory(){
        return new Associado(1L,"Joao","256.525");
    }
    public static Associado associadoSemIdFactory(){
        return new Associado("Joao","256.525");
    }
    public static Associado associadoFactory2(){
        return new Associado(1L,"Jose","256.898");
    }

    public static AssociadoDto associadoDtoFactory(){
        return new AssociadoDto("Joao","256.525");
    }
    public static List<AssociadoDto> ListaDeAssociadosDtoFactory(){
        return List.of(new AssociadoDto("Joao","256.525"),
                new AssociadoDto("Maria","6665.554"),
                new AssociadoDto("Joaquim","5485.98441"));
    }
}
