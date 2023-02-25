package com.dbserver.desafio.votacao.repository.entity;

import lombok.Data;
import lombok.experimental.FieldNameConstants;

import javax.persistence.*;

@Entity
@Table(name = "pauta")
@Data
@FieldNameConstants
public class PautaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String nome;
    private String descricao;

    @ManyToOne
    @JoinColumn(name = "idSessao")
    private SessaoEntity sessao;

}
