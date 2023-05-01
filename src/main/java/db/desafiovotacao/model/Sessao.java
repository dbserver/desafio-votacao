package db.desafiovotacao.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Embeddable
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class Sessao {

    private LocalDateTime inicioSessao;

    private LocalDateTime finalSessao;
}

