package com.db.votacao.api.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.antlr.v4.runtime.misc.NotNull;

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
    @ApiModelProperty(notes = "ID único da sessão")
    private UUID idSessao;

    @Column(name = "data_criacao", nullable = false)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(notes = "Data de criação da sessão")
    private LocalDateTime dataCriacao = LocalDateTime.now();

    @NotNull
    @Column(name = "inicio_sessao", nullable = false)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(notes = "Data e hora de início da sessão")
    private LocalDateTime inicioSessao;

    @Column(name = "final_sessao", nullable = false)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(notes = "Data e hora de encerramento da sessão")
    private LocalDateTime finalSessao;

    @OneToMany(mappedBy = "sessao", cascade = CascadeType.ALL)
    private List<Pauta> pautas;
}
