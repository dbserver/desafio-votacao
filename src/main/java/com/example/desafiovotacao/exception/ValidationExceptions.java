package com.example.desafiovotacao.exception;

public class ValidationExceptions extends RuntimeException{

    private static String FAULTY_INFORMATION = "Os dados fornecidos não estão de acordo com as obrigatoriedades desta requisição";
    private static String INVALID_CPF = "O CPF informado é invalido. Verifique seus dados";

    public ValidationExceptions(String message) {
        super(message);
    }

    public static void faultyInformation(){
        throw new ValidationExceptions(FAULTY_INFORMATION);
    }
    public static void invalidCpf() {
        throw new ValidationExceptions(INVALID_CPF);
    }
}
