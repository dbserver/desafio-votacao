package db.desafiovotacao.dto;

import db.desafiovotacao.enums.EnumVoto;
import db.desafiovotacao.model.Voto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.format.DateTimeFormatter;

public record VotoRequest(
        @NotNull(message = "voto não pode ser nulo")
        EnumVoto voto,

        @NotBlank(message = "data não pode estar em branco")
        String dataHoraVoto
) {
        private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        public VotoRequest(Voto voto){
                this(voto.getVoto(), voto.getDataHoraVoto().format(dateTimeFormatter));
        }
}
