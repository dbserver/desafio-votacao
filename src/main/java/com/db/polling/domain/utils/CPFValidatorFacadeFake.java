package com.db.polling.domain.utils;

import java.util.Random;

public class CPFValidatorFacadeFake {
    private static final Random random = new Random();

    public static boolean isCPFValid(String cpf) {
        return random.nextBoolean();
    }
}