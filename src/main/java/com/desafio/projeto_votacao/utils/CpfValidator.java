package com.desafio.projeto_votacao.utils;

import org.springframework.stereotype.Component;

@Component
public class CpfValidator {

    private CpfValidator(){
    }

    public static boolean isValid(String cpf) {
        cpf = cpf.replaceAll("\\D", "");

        if (cpf.length() != 11) {
            return false;
        }

        if (cpf.matches("(\\d)\\1{10}")) {
            return false;
        }

        int[] digits = cpf.chars().map(Character::getNumericValue).toArray();

        int firstVerifier = calculateVerifier(digits, 9);
        if (digits[9] != firstVerifier) {
            return false;
        }

        int secondVerifier = calculateVerifier(digits, 10);
        if (digits[10] != secondVerifier) {
            return digits[10] == secondVerifier;
        }

        return true;
    }

    private static int calculateVerifier(int[] digits, int start) {
        int sum = 0;
        int weight = start + 1;

        for (int i = 0; i < start; i++) {
            sum += digits[i] * weight;
            weight--;
        }

        int verifier = sum % 11;

        if (verifier < 2) {
            return 0;
        } else {
            return 11 - verifier;
        }
    }
}