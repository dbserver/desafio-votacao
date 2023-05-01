package db.desafiovotacao.mappers;

import db.desafiovotacao.dto.VotoRequest;
import db.desafiovotacao.model.Voto;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class VotoMapper {

    public static Voto mappearVoto(VotoRequest votoRequest){

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        return Voto.builder()
                .voto(votoRequest.voto())
                .dataHoraVoto(LocalDateTime.parse(votoRequest.dataHoraVoto(), dateTimeFormatter))
                .build();
    }
}
