package br.com.dbserver.voting.helpers;

import br.com.dbserver.voting.dtos.AssociateDTO;
import br.com.dbserver.voting.models.Associate;

import java.util.UUID;

public class AssociateCreator {

    public static AssociateDTO associateDTOValid(){
        return new AssociateDTO(UUID.fromString("d6df5158-cd61-48f3-a8cb-0660c24d1a23"), "user", "357.672.271-87");
    }

    public static AssociateDTO createAssociateDtoValid(){
        return new AssociateDTO(null, "user", "357.672.271-87");
    }

    public static AssociateDTO createAssociateDtoInvalid(){
        return new AssociateDTO(null, "user", null);
    }

    public static Associate associateValid(){
        return new Associate(UUID.fromString("d6df5158-cd61-48f3-a8cb-0660c24d1a23"), "user", "357.672.271-87");
    }
}
