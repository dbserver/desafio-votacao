package com.example.desafiovotacao.utils;

import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class CpfUtils {

    public static Boolean validateCPF(String cpf) {
        cpf = cpf.replaceAll("[^0-9]", "");
        if (cpf.length() != 11) {
            return false;
        }
        if (cpf.matches("(\\d)\\1{10}")) {
            return false;
        }

        int[] cpfArray = cpf.chars().map(Character::getNumericValue).toArray();
        int sum = 0;
        for (int i = 0; i < 9; i++) {
            sum += cpfArray[i] * (10 - i);
        }
        int firstDigit = (sum * 10) % 11;

        sum = 0;
        for (int i = 0; i < 10; i++) {
            sum += cpfArray[i] * (11 - i);
        }
        int secondDigit = (sum * 10) % 11;

        return (firstDigit == cpfArray[9] && secondDigit == cpfArray[10]);
    }

    public static String generateCPF() {
        int[] cpf = new int[11];
        for (int i = 0; i < 9; i++) {
            cpf[i] = random(9);
        }

        cpf[9] = calculateVerifierDigit(cpf, 9, 2);
        cpf[10] = calculateVerifierDigit(cpf, 10, 11);

        StringBuilder cpfBuilder = new StringBuilder();
        for (int i = 0; i < 11; i++) {
            cpfBuilder.append(cpf[i]);
        }

        return cpfBuilder.toString();
    }

    public static Boolean facadeRandomCpf(String cpf) {
        return new Random().nextBoolean();
    }

    private static int random(int n) {
        return (int) (Math.random() * (n + 1));
    }

    private static int calculateVerifierDigit(int[] cpf, int posicaoDigito, int multiplicadorInicial) {
        int total = 0;
        for (int i = 0; i < posicaoDigito; i++) {
            total += cpf[i] * multiplicadorInicial;
            multiplicadorInicial--;
        }

        int resto = total % 11;
        return (resto < 2) ? 0 : 11 - resto;
    }
}
