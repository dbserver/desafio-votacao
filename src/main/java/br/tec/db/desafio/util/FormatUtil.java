package br.tec.db.desafio.util;

import javax.swing.text.MaskFormatter;
import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class FormatUtil {
    public static final String CPF = "###.###.###-##";
    private static final String PATTERN_FORMAT = "dd.MM.yyyy HH:mm:ss";

    public String cpfComCaracteresEspeciais(String dado) {
        try {
            MaskFormatter mask = new MaskFormatter(CPF);
            mask.setValueContainsLiteralCharacters(false);
            return mask.valueToString(dado);
        } catch (Exception e) {
            return " ";
        }
    }

    public String dataFormatada(String dado) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(PATTERN_FORMAT)
                    .withZone(ZoneId.systemDefault());
            return formatter.format(Instant.parse(dado));
        } catch (Exception e) {
            return " ";
        }
    }
}
