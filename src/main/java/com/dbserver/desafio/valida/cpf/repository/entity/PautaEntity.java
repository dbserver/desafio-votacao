package com.dbserver.desafio.valida.cpf.repository.entity;

import lombok.Data;
import lombok.experimental.FieldNameConstants;

import javax.persistence.*;

@Entity
@Table(name = "pauta")
@Data
@FieldNameConstants
public class PautaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sessao_id_generator")
    @SequenceGenerator(name = "sessao_id_generator", sequenceName = "sessao_id_seq", allocationSize = 1)
    private Integer idPauta;

    private String nome;

    private String descricao;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "idSessao")
    private SessaoEntity sessao;

}
