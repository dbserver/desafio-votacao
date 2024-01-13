package br.com.dbserver.voting.helpers;

import br.com.caelum.stella.validation.CPFValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Util {

    private static final Logger logger = LoggerFactory.getLogger(Util.class);

    public static String localDateTimeToString(LocalDateTime localDateTime){
        return DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss").format(localDateTime);
    }

    public static String removeNonNumericCharacterFromCpf(String cpf){
        return cpf.replaceAll("[^0-9]", "");
    }

    public static boolean validCpf(String cpf) {
        CPFValidator cpfValidator = new CPFValidator();
        try{
            if(cpf.length() > 11){
                cpf = removeNonNumericCharacterFromCpf(cpf);
            }
            cpfValidator.assertValid(cpf);
            return true;
        }catch(Exception e){
            logger.info(e.getMessage());
            return false;
        }
    }
}
