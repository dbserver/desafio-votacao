package br.tec.db.votacao.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "pauta")
@EqualsAndHashCode(of = "id")
public class Pauta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;

    @OneToOne
    private SessaoDeVotacao sessaoDeVotacao;

    @ManyToOne
    private Assembleia assembleia;
}
