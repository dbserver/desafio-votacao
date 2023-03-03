package br.tec.db.desafio.exception;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class NotFoundExceptionTest {
    private static final String ERROR = "Error";
    @Test
    void deveRetornarMsgComSucesso() {
        NotFoundException actual = new NotFoundException(ERROR);
        NotFoundException expected = new NotFoundException(ERROR);

        assertThat(expected).usingRecursiveComparison()
                .isEqualTo(actual);
    }
}
