package com.fernandesclaudi.desafiovotacao.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@Table(name = "sessao")
public class Sessao {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "duracao")
    private Long duracao;

    @ManyToOne
    @JoinColumn(name = "idpauta", referencedColumnName = "id")
    private Pauta pauta;

    @Column(name = "dtinicio")
    private LocalDateTime dataInicio;
    @Column(name = "dtfim")
    private LocalDateTime dataFim;

    @OneToMany(fetch = FetchType.LAZY)
    private List<Voto> votos;
}
