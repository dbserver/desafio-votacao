package com.db.votacao.api.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Table(name = Associado.TABLE_NAME)
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Associado {

    public static final String TABLE_NAME = "associado";

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_associado", unique = true)
    private UUID idAssociado;

    @Column(name = "cpf", nullable = false, unique = true, length = 14)
    private String cpf;

}
