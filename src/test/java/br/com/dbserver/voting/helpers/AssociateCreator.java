package br.com.dbserver.voting.helpers;

import br.com.dbserver.voting.dtos.AssociateDTO;
import br.com.dbserver.voting.models.Associate;

import java.util.UUID;

public class AssociateCreator {

    public static AssociateDTO associateDTOValid(){
        return new AssociateDTO(1, "user", "357.672.271-87");
    }

    public static AssociateDTO createAssociateDtoValid(){
        return new AssociateDTO(null, "user", "357.672.271-87");
    }

    public static AssociateDTO createAssociateDtoInvalid(){
        return new AssociateDTO(null, "user", null);
    }

    public static Associate associateValid(){
        return new Associate(1, "user", "357.672.271-87");
    }
}
