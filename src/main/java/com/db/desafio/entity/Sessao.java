package com.db.desafio.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@Entity
@ToString
@Table(name = "tb_sessao")
public class Sessao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    private Pauta pauta;
    @Column(name = "inicioSessao")
    private LocalDateTime inicioSessao = LocalDateTime.now();
    @Column(name = "finalsessao")
    private LocalDateTime finalSessao = inicioSessao.plusMinutes(1);
    @OneToMany(mappedBy = "sessao", cascade = {CascadeType.ALL})
    private List<Voto> votos ;

    public Sessao(Pauta pauta) {
        this.pauta = pauta;
    }

    public Sessao(Long id, Pauta pauta, LocalDateTime inicioSessao, LocalDateTime finalSessao) {
        this.id = id;
        this.pauta = pauta;
        this.inicioSessao = inicioSessao;
        this.finalSessao = finalSessao;
    }

    public Sessao(Pauta pauta, LocalDateTime inicioSessao, LocalDateTime finalSessao) {
        this.pauta = pauta;
        this.inicioSessao = inicioSessao;
        this.finalSessao = finalSessao;
    }
}


