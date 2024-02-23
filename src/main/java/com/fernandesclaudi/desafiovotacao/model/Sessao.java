package com.fernandesclaudi.desafiovotacao.model;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;
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

    @OneToOne
    @JoinColumn(name = "idpauta", referencedColumnName = "id")
    private Pauta pauta;

    @Column(name = "dtinicio")
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime dataInicio;
    @Column(name = "dtfim")
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime dataFim;

    @OneToMany(fetch = FetchType.LAZY)
    private List<Voto> votos;
}
