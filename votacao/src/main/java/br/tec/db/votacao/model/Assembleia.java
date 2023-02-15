package br.tec.db.votacao.model;

import br.tec.db.votacao.enums.AssembleiaStatusEnum;
import jakarta.persistence.*;
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

    private LocalDateTime inicio = LocalDateTime.now();

    private LocalDateTime fim = LocalDateTime.now().plusHours(2);

    @OneToMany
    private List<Associado> associados;

    @OneToMany
    private List<Pauta> pautas;

    @Enumerated(EnumType.STRING)
    private AssembleiaStatusEnum status;
}
