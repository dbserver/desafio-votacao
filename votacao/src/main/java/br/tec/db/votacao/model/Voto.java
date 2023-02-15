package br.tec.db.votacao.model;

import br.tec.db.votacao.enums.VotoStatusEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "voto")
@EqualsAndHashCode(of = "id")
public class Voto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private Associado associado;

    @ManyToOne
    private SessaoDeVotacao sessaoDeVotacao;

    @Enumerated(EnumType.STRING)
    private VotoStatusEnum status;
}
