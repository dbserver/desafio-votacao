package db.desafiovotacao.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "associados_pauta")
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AssociadoPauta {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Boolean votou;

    @ManyToOne(fetch = FetchType.LAZY)
    private Associado associado;

    @ManyToOne(fetch = FetchType.LAZY)
    private Pauta pauta;
}
