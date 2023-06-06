package com.example.votacaodesafio.domain.entity;

import com.example.votacaodesafio.domain.enums.VotoEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "votacao")
public class Votacao implements DTOEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "sessao_id", nullable = false)
    private SessaoVotacao sessaoVotacao;

    @ManyToOne
    @JoinColumn(name = "assosciado_id", nullable = false)
    private Assosciado assosciado;

    @Column(name = "voto", nullable = false)
    @Enumerated(EnumType.STRING)
    private VotoEnum vote;

    @CreationTimestamp
    @Column(name = "criacao", nullable = false)
    private LocalDateTime criacao;
}
