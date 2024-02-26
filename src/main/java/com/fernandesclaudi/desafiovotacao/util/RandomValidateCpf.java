package com.fernandesclaudi.desafiovotacao.util;

import java.util.Random;

public class RandomValidateCpf {

    public static String generateCpf() {
        String cpf = "";
        for (int i = 0; i < 9; i++) {
            cpf += (int) (Math.random() * 9) + 1;
        }
        return cpf;
    }

    public static boolean validarCpfRandom(String cpf) {
        Random random = new Random();
        return random.nextBoolean();
    }
}
