package com.fernandesclaudi.desafiovotacao.model;

import jakarta.persistence.Entity;
import lombok.Data;

@Data
@Entity()
public class Associado {

    private Long id;
    private String nome;
    private String cpf;
}
