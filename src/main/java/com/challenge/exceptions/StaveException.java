package com.challenge.exceptions;

public class StaveException extends RuntimeException {

    private static final String SESSION_STILL_OPEN_MESSAGE = "Sessão está aberta para votação!";

    public StaveException(String message) {
        super(message);
    }

    public static void sessionStillOpen() {
        throw new StaveException(SESSION_STILL_OPEN_MESSAGE);
    }
}
