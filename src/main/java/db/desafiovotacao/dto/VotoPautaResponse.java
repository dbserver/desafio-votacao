package db.desafiovotacao.dto;

import db.desafiovotacao.model.VotoPauta;

public record VotoPautaResponse(

        Long idPauta,

        String cpf

) {

    public VotoPautaResponse(VotoPauta votoPauta, String cpf){
        this(votoPauta.getPauta().getId(), cpf);
    }
}
