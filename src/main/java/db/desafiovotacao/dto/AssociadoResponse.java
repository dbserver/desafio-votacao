package db.desafiovotacao.dto;

import db.desafiovotacao.model.Associado;

public record AssociadoResponse(
        String cpf
) {

    public AssociadoResponse(Associado associado){
        this(associado.getCPF());
    }
}
