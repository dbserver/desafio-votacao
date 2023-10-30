package com.example.desafiovotacao.exception;

public class SessionExceptions extends RuntimeException{

    private static String SESSION_DOES_NOT_EXIST = "A sessão informada não existe";
    private static String SESSION_IS_STILL_RUNNING = "A sessão ainda está em andamento, aguarde a finalização dela para realizar a contagem de votos";

    public SessionExceptions(String message){
        super(message);
    }

    public static void sessionDontExist(){
        throw new SessionExceptions(SESSION_DOES_NOT_EXIST);
    }

    public static void sessionStillRunning() {
        throw new SessionExceptions(SESSION_IS_STILL_RUNNING);
    }
}
