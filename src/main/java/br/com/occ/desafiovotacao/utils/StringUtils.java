/*
 *  StringUtils
 *
 *  1.0.0
 *
 *  © Copyright 2018, Instituto de Gestão Previdenciária do Estado do Pará
 *  http://www.igeprev.pa.gov.br/
 *  Todos os direitos reservados e protegidos pela Lei nº9.610/98.
 */
package br.com.occ.desafiovotacao.utils;


import javax.swing.text.MaskFormatter;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.Normalizer;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Base64;
import java.util.List;
import java.util.Locale;
public class StringUtils {
    public static String toLowerCase(String str) {
        return str != null ? str.toLowerCase() : null;
    }

    public static String emptyIsNull(String str) {
        return str != null && !"".equals(str.trim()) ? str.trim() : null;
    }

    public static String toLowerCaseEmpty(String str) {
        return str != null && str.trim().length() > 0 ? str.toLowerCase() : null;
    }

    public static String toLowerCaseTrimEmpty(String str) {
        return str != null && str.trim().length() > 0 ? str.toLowerCase().trim() : null;
    }

    public static boolean isEmpty(String str) {
        return str == null || "".equals(str.trim());
    }

    public static String removeSpecialCharacter(String str) {
        return str != null && str.trim().length() > 0 ?
                str.replaceAll("\\(", "")
                        .replaceAll("\\)", "")
                        .replaceAll("\\.", "")
                        .replaceAll("\\/", "")
                        .replaceAll("\\_", "")
                        .replaceAll("_", "")
                        .replaceAll("-", "")
                        .replaceAll("[- /.]", "")
                        .replaceAll("[-()]", "") : null;
    }

    public static String convertNull(String str) {
        return str != null && !str.equalsIgnoreCase("null") ? str : null;
    }

    public static String byteToString(byte[] b) {
        String str = Base64.getEncoder().encodeToString(b);
        return str;
    }

    public static byte[] stringToByte(String str) {
        return Base64.getDecoder().decode(str);
    }
    
    public static String preencheZeros(long numero, int quantidade) {
        String temp = String.valueOf(numero);
        String retorno = "";

        if (quantidade < temp.length())
            return temp;
        else {
            for (int i = 0; i < (quantidade - temp.length()); i++)
                retorno = "0" + retorno;
            return retorno + temp;
        }
    }

    public static String formatString(String value, String pattern) {
        MaskFormatter mf;
        try {
            mf = new MaskFormatter(pattern);
            mf.setValueContainsLiteralCharacters(false);
            return mf.valueToString(value);
        } catch (ParseException ex) {
            return value;
        }
    }

    public static String formatValor(BigDecimal bd) {
        Locale ptBr = new Locale("pt", "BR");
        return NumberFormat.getCurrencyInstance(ptBr).format(bd);
    }

    public static String formatValorPorcentagem(BigDecimal bd) {
        bd = bd.divide(new BigDecimal(100));
        NumberFormat defaultFormat = NumberFormat.getPercentInstance();
        defaultFormat.setMinimumFractionDigits(2);
        return defaultFormat.format(bd);
    }

    public static String formatedIdAndName(int id, String name) {
        StringBuilder builder = new StringBuilder();
        builder.append(id);
        builder.append(" - ");
        builder.append(name);
        return builder.toString();
    }

    public static String stripAccentsAndRemoveBlankSpace(String s) {
        return removeBlankSpace(removePreposition(stripAccents(s))).toLowerCase();
    }

    public static String stripAccents(String s) {
        s = Normalizer.normalize(s, Normalizer.Form.NFD);
        s = s.replaceAll("[\\p{InCombiningDiacriticalMarks}]", "");
        return s;
    }

    public static String doubleToStr(double d) {
        DecimalFormat df = new DecimalFormat("#");
        df.setMaximumFractionDigits(0);

        return df.format(d);
    }

    public static String removePreposition(String s) {
        String padrao = "(\\w)(\\s+)(e|do|da|do|das|de|di|du)(\\s+)(\\w)";
        return s.replaceAll(padrao, "$1 $5");
    }

    public static String formataCPF(String CPF) {
        return(CPF.substring(0, 3) + "." + CPF.substring(3, 6) + "." +
                CPF.substring(6, 9) + "-" + CPF.substring(9, 11));
    }

    public static String removeBlankSpace(String s) {
        return s.replaceAll("\\s+", "");
    }

    public static String onlyDigits(String str) {
        return str.replaceAll("\\D+", "");
    }

    public static String getStringByArray(List<Long> numbers) {
        if (numbers != null) {
            String replace = numbers.toString()
                    .replace("]", "")
                    .replace("[", "")
                    .replace(" ", "");
            if (numbers.size() > 0)
                return replace;
        }
        return null;
    }

}
