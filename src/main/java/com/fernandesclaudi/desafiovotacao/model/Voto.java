package com.fernandesclaudi.desafiovotacao.model;

import com.fernandesclaudi.desafiovotacao.enums.VotoEnum;
import jakarta.persistence.Entity;

import java.time.LocalDateTime;

@Entity
public class Voto {

    private Long id;
    private Associado associado;
    private VotoEnum voto;
    private LocalDateTime dataHora;
}
