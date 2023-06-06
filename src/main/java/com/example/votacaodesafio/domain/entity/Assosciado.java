package com.example.votacaodesafio.domain.entity;


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
@Table(name = "assosciado")
public class Assosciado implements DTOEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    @Column(name = "cpf", nullable = false, unique = true)
    private String cpf;

    @CreationTimestamp
    @Column(name = "data", nullable = false)
    private LocalDateTime data;

}
