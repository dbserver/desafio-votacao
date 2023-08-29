package com.db.desafio.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "tb_sessao")
public class Sessao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    @JoinColumn(name = "pauta_id")
    private Pauta pauta;
    @Column(name = "inicioSessao")
    private LocalDateTime inicioSessao = LocalDateTime.now();
    @Column(name = "finalsessao")
    private LocalDateTime finalSessao = inicioSessao.plusMinutes(1);

    public Sessao(Pauta pauta) {
        this.pauta = pauta;
    }

    public Sessao(Long id, Pauta pauta, LocalDateTime inicioSessao, LocalDateTime finalSessao) {
        this.id = id;
        this.pauta = pauta;
        this.inicioSessao = inicioSessao;
        this.finalSessao = finalSessao;
    }
}
