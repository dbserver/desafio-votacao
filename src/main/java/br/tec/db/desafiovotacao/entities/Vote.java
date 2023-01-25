package br.tec.db.desafiovotacao.entities;

import jakarta.persistence.*;
import lombok.Data;


@Data
@Entity
public class Vote {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private Associate associate;

    @Column(nullable = false)
    private Answer answer;

    @OneToOne
    private VotingAgenda agenda;

    enum Answer{
        YES,
        NO;
    }
}
