package com.db.votacao.api.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = Sessao.TABLE_NAME)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Sessao {

    public static final String TABLE_NAME = "sessao";

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_sessao", unique = true)
    private UUID idSessao;

    @Column(name = "data_criacao", nullable = false)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime dataCriacao = LocalDateTime.now();

    @Column(name = "inicio_sessao", nullable = false)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime inicioSessao;

    @Column(name = "final_sessao", nullable = false)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime finalSessao;

    @OneToMany(mappedBy = "sessao", cascade = CascadeType.ALL)
    private List<Pauta> pautas;
}
