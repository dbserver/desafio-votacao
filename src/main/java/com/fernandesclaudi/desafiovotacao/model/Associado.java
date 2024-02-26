package com.fernandesclaudi.desafiovotacao.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "associado")
public class Associado {
    @Id
    @SequenceGenerator(name = "seq_associado", sequenceName = "seq_associado", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_associado")
    private Long id;
    @Column(name = "nome")
    private String nome;
    @Column(name = "cpf")
    private String cpf;
}
