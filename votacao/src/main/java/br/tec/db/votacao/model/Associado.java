package br.tec.db.votacao.model;

import br.tec.db.votacao.enums.AssociadoStatusEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "associado")
@EqualsAndHashCode(of = "id")
public class Associado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private String cpf;

    @OneToOne
    private Voto voto;

    @ManyToOne
    private Assembleia assembleia;

    @Enumerated(EnumType.STRING)
    private AssociadoStatusEnum status;
}
