package com.example.desafiovotacao.exception;

public class AssociateExceptions extends RuntimeException{

    private static String ASSOCIATE_NOT_FOUND = "Associado não encontrado";

    private static String ASSOCIATE_ALERADY_REGISTERED = "Associado já está cadastrado no sistema";

    public AssociateExceptions(String message) {
        super(message);
    }

    public static void associateAlreadyRegistered(){
        throw new AssociateExceptions(ASSOCIATE_ALERADY_REGISTERED);
    }

    public static void associateNotFound() {
        throw new AssociateExceptions(ASSOCIATE_NOT_FOUND);
    }
}
