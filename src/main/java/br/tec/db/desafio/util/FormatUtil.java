package br.tec.db.desafio.util;

import javax.swing.text.MaskFormatter;

public class FormatUtil {
    public static final String CPF = "###.###.###-##";
    public String cpfComCaracteresEspeciais(String dado) {
        try {
            MaskFormatter mask = new MaskFormatter(CPF);
            mask.setValueContainsLiteralCharacters(false);
            return mask.valueToString(dado);
        } catch (Exception e) {
            return " ";
        }
    }
}
