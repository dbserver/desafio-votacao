package com.db.api.models;

import com.db.api.enums.ResultadoSessao;
import com.db.api.enums.StatusSessao;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "sessoes")
public class Sessao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @ManyToOne
    private Pauta pauta;
    @Column(name = "data_abertura")
    private LocalDateTime dataAbertura = LocalDateTime.now();
    @Column(name = "data_encerramento")
    private LocalDateTime dataEncerramento = dataAbertura.plusMinutes(1);
    @Enumerated(EnumType.STRING)
    private StatusSessao statusSessao = StatusSessao.ABERTA;
    @Enumerated(EnumType.STRING)
    private ResultadoSessao resultadoSessao;


    public Sessao(Pauta pauta) {
        this.pauta = pauta;
    }

    public void setDataEncerramento(LocalDateTime dataEncerramento) {
        this.dataEncerramento = dataEncerramento;
    }

    public void setStatusSessao(StatusSessao statusSessao) {
        this.statusSessao = statusSessao;
    }

    public void setResultadoSessao(ResultadoSessao resultadoSessao) {
        this.resultadoSessao = resultadoSessao;
    }
}
