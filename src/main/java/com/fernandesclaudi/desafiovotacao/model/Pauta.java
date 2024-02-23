package com.fernandesclaudi.desafiovotacao.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
@Table(name = "pauta")
public class Pauta {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "titulo")
    private String titulo;
    @OneToOne
    @JoinColumn(name = "idredator", referencedColumnName = "id")
    private Associado redator;
    @Column(name = "dtpauta")
    private LocalDate data;

}
