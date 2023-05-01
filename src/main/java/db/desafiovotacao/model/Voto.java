package db.desafiovotacao.model;


import db.desafiovotacao.enums.EnumVoto;
import jakarta.persistence.*;

import lombok.*;

import java.time.LocalDateTime;


@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class Voto {

    @Enumerated(EnumType.STRING)
    private EnumVoto voto;

    private LocalDateTime dataHoraVoto;
}
