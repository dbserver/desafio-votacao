package br.tec.db.votacao.model;

import br.tec.db.votacao.enums.VotoStatusEnum;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "voto")
@EqualsAndHashCode(of = "id")
public class Voto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private VotoStatusEnum status;

    @ManyToOne
    @JoinColumn(name = "sessao_de_votacao_id")
    private SessaoDeVotacao sessaoDeVotacao;

    @OneToOne
    @JoinTable(name = "voto_associado", joinColumns = @JoinColumn(name = "voto_id"),
            inverseJoinColumns = @JoinColumn(name = "associado_id"))
    private Associado associado;

}
