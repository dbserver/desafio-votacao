package com.example.votacaodesafio.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "pauta")
public class Pauta implements DTOEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;

    private String descricao;

    @OneToMany(mappedBy = "pauta")
    private List<SessaoVotacao> sessoesVotacao = new ArrayList<>();

    @CreationTimestamp
    @Column(name = "data", nullable = false)
    private LocalDateTime data;
}
