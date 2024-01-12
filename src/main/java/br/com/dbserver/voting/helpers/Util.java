package br.com.dbserver.voting.helpers;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Util {
    public static String localDateTimeToString(LocalDateTime localDateTime){
        return DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss").format(localDateTime);
    }
}
