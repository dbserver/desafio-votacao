package br.tec.db.votacao.model;

import br.tec.db.votacao.enums.AssembleiaStatusEnum;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "assembleia")
@EqualsAndHashCode(of = "id")
public class Assembleia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime inicio = LocalDateTime.now();

    private LocalDateTime fim = LocalDateTime.now().plusHours(2);

    @Setter
    @Enumerated(EnumType.STRING)
    private AssembleiaStatusEnum status;

    @OneToMany
    @JoinTable(name = "assembleia_associados", joinColumns = @JoinColumn(name = "assembleia_id"),
            inverseJoinColumns = @JoinColumn(name = "associado_id"))
    private List<Associado> associados;

    @OneToMany
    @JoinTable(name = "assembleia_pautas", joinColumns = @JoinColumn(name = "assembleia_id"),
            inverseJoinColumns = @JoinColumn(name = "pauta_id"))
    private List<Pauta> pautas;

}
