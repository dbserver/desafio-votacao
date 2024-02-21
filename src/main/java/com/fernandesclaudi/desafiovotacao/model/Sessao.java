package com.fernandesclaudi.desafiovotacao.model;

import jakarta.persistence.Entity;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
public class Sessao {

    private Long id;
    private Long duracao;
    private Pauta pauta;
    private LocalDateTime dataInicio;
    private LocalDateTime dataFim;

    private List<Voto> votos;
}
