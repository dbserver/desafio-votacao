package br.tec.db.votacao.model;

import br.tec.db.votacao.enums.AssociadoStatusEnum;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
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

    @NotBlank
    private String nome;

    @NotBlank
    @Pattern(regexp = "[0-9]{11}", message = "CPF deve conter apenas números")
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
