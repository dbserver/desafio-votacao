package db.desafiovotacao.model;

import db.desafiovotacao.dto.VotoRequest;
import db.desafiovotacao.enums.EnumVoto;
import jakarta.persistence.*;

import lombok.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Voto {

    @Column(name = "opcao_voto")
    @Enumerated(EnumType.STRING)
    private EnumVoto voto;

    private LocalDateTime dataHoraVoto;

    public Voto(VotoRequest votoRequest){

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        this.voto = votoRequest.voto();
        this.dataHoraVoto = LocalDateTime.parse(votoRequest.dataHoraVoto(), dateTimeFormatter);
    }

}
