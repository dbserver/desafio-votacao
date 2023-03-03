package br.tec.db.desafio.exception;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class BusinessExceptionTest {
    private static final String ERROR = "Error";
    @Test
    void deveRetornarMsgComSucesso() {
        BusinessException actual = new BusinessException(ERROR);
        BusinessException expected = new BusinessException(ERROR);

        assertThat(expected).usingRecursiveComparison()
                .isEqualTo(actual);
    }
}
