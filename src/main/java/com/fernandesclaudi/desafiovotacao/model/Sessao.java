package com.fernandesclaudi.desafiovotacao.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "sessao")
public class Sessao {

    @Id
    @SequenceGenerator(name = "seq_sessao", sequenceName = "seq_sessao", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_sessao")
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
}
