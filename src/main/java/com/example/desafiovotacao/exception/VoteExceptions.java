package com.example.desafiovotacao.exception;

public class VoteExceptions extends RuntimeException{

    private static String SESSION_CLOSED_MESSAGE = "Sessão de votação encerrada";
    private static String CPF_ALREADY_VOTED_ON_SESSION = "Este associado já votou nesta sessão";

    public VoteExceptions(String message){
        super(message);
    }

    public static void sessionClosed() {
        throw new VoteExceptions(SESSION_CLOSED_MESSAGE);
    }

    public static void alreadyVoted() {
        throw new VoteExceptions(CPF_ALREADY_VOTED_ON_SESSION);
    }
}
