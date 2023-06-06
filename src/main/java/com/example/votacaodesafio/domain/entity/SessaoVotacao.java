package com.example.votacaodesafio.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "sessao_votacao")
public class SessaoVotacao implements DTOEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "pauta_id", nullable = false)
    private Pauta pauta;

    @CreationTimestamp
    @Column(name = "data", nullable = false)
    private LocalDateTime data;

    @Column(name = "abertura", nullable = false)
    private LocalDateTime abertura;

    @Column(name = "fechamento", nullable = false)
    private LocalDateTime fechamento;

    @OneToMany(mappedBy = "sessaoVotacao")
    private List<Votacao> listaVotos = new ArrayList<>();

    public boolean ehSessaoValida(int durationMinutes) {
        LocalDateTime now = LocalDateTime.now();

        if ((isNull(abertura) || abertura.isAfter(now)) || (nonNull(fechamento) && fechamento.isBefore(abertura))) {
            return false;
        }

        if (fechamento == null || fechamento.isBefore(fechamento.plusMinutes(durationMinutes))) {
            fechamento = abertura.plusMinutes(durationMinutes);
        }

        return true;
    }

    public boolean ehSessaoFechada() {
        return fechamento != null && fechamento.isBefore(LocalDateTime.now());
    }

}
