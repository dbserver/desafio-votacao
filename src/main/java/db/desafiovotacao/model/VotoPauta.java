package db.desafiovotacao.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "votos_pauta")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VotoPauta {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Embedded
    private Voto voto;

    @ManyToOne(fetch = FetchType.LAZY)
    private Pauta pauta;
}
