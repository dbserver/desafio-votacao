package com.db.desafio.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "tb_sessaoVotacao")
public class SessaoVotacao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    private Pauta pauta;
    @Column(name = "inicioSessao")
    private LocalDateTime inicioSessao = LocalDateTime.now();
    @Column(name = "finalSessao")
    private LocalDateTime finalSessao = inicioSessao.plusMinutes(1);
    @OneToMany(mappedBy = "sessaoVotacao", cascade = {CascadeType.ALL})
    private List<Voto> votos ;

    public SessaoVotacao(Pauta pauta) {
        this.pauta = pauta;
    }

    public SessaoVotacao(Long id, Pauta pauta, LocalDateTime inicioSessao, LocalDateTime finalSessao) {
        this.id = id;
        this.pauta = pauta;
        this.inicioSessao = inicioSessao;
        this.finalSessao = finalSessao;
    }

    public SessaoVotacao(Pauta pauta, LocalDateTime inicioSessao, LocalDateTime finalSessao) {
        this.pauta = pauta;
        this.inicioSessao = inicioSessao;
        this.finalSessao = finalSessao;
    }
}


