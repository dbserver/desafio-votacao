package db.desafiovotacao.mappers;

import db.desafiovotacao.dto.VotoPautaRequest;
import db.desafiovotacao.model.Pauta;
import db.desafiovotacao.model.VotoPauta;

public class VotoPautaMapper {

    public static VotoPauta mappearVotoPauta(VotoPautaRequest votoPautaRequest, Pauta pauta){

        VotoPauta votoPauta = new VotoPauta();

        return VotoPauta.builder()
                .voto(VotoMapper.mappearVoto(votoPautaRequest.voto()))
                .pauta(pauta)
                .build();
    }
}
