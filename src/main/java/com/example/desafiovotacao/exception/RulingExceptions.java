package com.example.desafiovotacao.exception;

public class RulingExceptions extends RuntimeException{

    private static String RULING_DOES_NOT_EXIST = "A pauta informada não está cadastrada";
    private static String RULING_HAS_ALREADY_ENDED = "A pauta já realizou uma sessão de votação e contabilizou um resultado";
    private static String VOTING_TIE = "A contabilização não pode ser realizada por conta de um empate na votação. Realize outra sessão e contabilize novamente";

    public RulingExceptions(String message){
        super(message);
    }

    public static void rulingDoesNotExist(){
        throw new RulingExceptions(RULING_DOES_NOT_EXIST);
    }

    public static void rulingHasAlreadyEnded(){
        throw new RulingExceptions(RULING_HAS_ALREADY_ENDED);
    }

    public static void rulingHasVotesTied() {
        throw new RulingExceptions(VOTING_TIE);
    }

}