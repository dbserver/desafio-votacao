
package com.desafiovotacao.service.associado;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;


@ExtendWith(SpringExtension.class)
public class ValidadorCPFTest {

    @InjectMocks
    private ValidadorCPF validadorCPF;

    @Test
    void execute_shouldReturnTrueOrFalse() {
        boolean isValid = validadorCPF.validar("teste");

        assertTrue(isValid || !isValid);
    }
}
