package br.tec.db.votacao.model;

import br.tec.db.votacao.enums.AssembleiaStatusEnum;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

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

    @NotBlank
    private LocalDateTime inicio = LocalDateTime.now();

    @NotBlank
    private LocalDateTime fim = LocalDateTime.now().plusHours(2);

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
