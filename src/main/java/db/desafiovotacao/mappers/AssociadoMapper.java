package db.desafiovotacao.mappers;

import db.desafiovotacao.dto.AssociadoRequest;
import db.desafiovotacao.model.Associado;

public class AssociadoMapper {

    public static Associado mappearAssociado(AssociadoRequest associadoRequest){

        Associado associado = new Associado();

        associado.setCPF(associadoRequest.cpf());

        return associado;
    }
}
