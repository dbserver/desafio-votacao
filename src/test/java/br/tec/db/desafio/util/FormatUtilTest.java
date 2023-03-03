package br.tec.db.desafio.util;

import org.junit.jupiter.api.Test;

import java.util.Calendar;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FormatUtilTest {
    @Test
    void deveFormatarCpf() {
        FormatUtil formatUtil = new FormatUtil();
        String cpfFormatado = formatUtil.cpfComCaracteresEspeciais("12312333445");

        assertEquals("123.123.334-45", cpfFormatado);
    }
}
