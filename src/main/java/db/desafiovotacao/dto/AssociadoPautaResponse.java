package db.desafiovotacao.dto;

import db.desafiovotacao.model.AssociadoPauta;

public record AssociadoPautaResponse(
        Long idPauta,

        String titulo,

        String cpf,

        Boolean votou
) {

    public AssociadoPautaResponse(AssociadoPauta associadoPauta){
        this(
                associadoPauta.getPauta().getId(),
                associadoPauta.getPauta().getTitulo(),
                associadoPauta.getAssociado().getCPF(),
                associadoPauta.getVotou()
        );
    }
}
