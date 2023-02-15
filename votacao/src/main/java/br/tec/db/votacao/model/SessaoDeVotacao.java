package br.tec.db.votacao.model;

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
@Table(name = "sessao_de_votacao")
@EqualsAndHashCode(of = "id")
public class SessaoDeVotacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime inicio = LocalDateTime.now();

    private LocalDateTime fim = LocalDateTime.now().plusMinutes(1);

    @OneToMany
    private List<Voto> votos;

    @OneToOne
    private Pauta pauta;

}
