package com.fernandesclaudi.desafiovotacao.model;

import jakarta.persistence.Entity;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
public class Pauta {

    private Long id;
    private String titulo;
    private Associado redator;
    private LocalDate data;

}
