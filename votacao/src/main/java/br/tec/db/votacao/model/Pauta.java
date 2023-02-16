package br.tec.db.votacao.model;

import br.tec.db.votacao.enums.PautaStatusEnum;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "pauta")
@EqualsAndHashCode(of = "id")
public class Pauta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String titulo;

    @Enumerated(EnumType.STRING)
    private PautaStatusEnum status;

    @ManyToOne
    @JoinColumn(name = "assembleia_id")
    private Assembleia assembleia;

    @OneToOne
    @JoinTable(name = "pauta_sessao_de_votacao", joinColumns = @JoinColumn(name = "pauta_id"),
            inverseJoinColumns = @JoinColumn(name = "sessao_de_votacao_id"))
    private SessaoDeVotacao sessaoDeVotacao;

}
