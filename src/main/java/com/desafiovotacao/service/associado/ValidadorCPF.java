
package com.desafiovotacao.service.associado;

import com.desafiovotacao.service.interfaces.IValidadorCPF;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class ValidadorCPF implements IValidadorCPF {

    @Override
    public boolean validar(String cpf) {
        Random random = new Random();
        return random.nextBoolean();
    }
}
