package com.fernandesclaudi.desafiovotacao.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "associado")
public class Associado {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "nome")
    private String nome;
    @Column(name = "cpf")
    private String cpf;
}
