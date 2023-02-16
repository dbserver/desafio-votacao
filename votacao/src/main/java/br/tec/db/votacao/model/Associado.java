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

    @Enumerated(EnumType.STRING)
    private AssociadoStatusEnum status;

    @OneToOne
    @JoinTable(name = "voto_associado", joinColumns = @JoinColumn(name = "associado_id"),
            inverseJoinColumns = @JoinColumn(name = "voto_id"))
    private Voto voto;

    @ManyToOne
    @JoinTable(name = "assembleia_associados", joinColumns = @JoinColumn(name = "associado_id"),
            inverseJoinColumns = @JoinColumn(name = "assembleia_id"))
    private Assembleia assembleia;

}
