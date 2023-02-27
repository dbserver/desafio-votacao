package com.dbserver.desafio.votacao.repository.entity;

import lombok.Data;
import lombok.experimental.FieldNameConstants;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "sessao")
@Data
@FieldNameConstants
public class SessaoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pauta_id_generator")
    @SequenceGenerator(name = "pauta_id_generator", sequenceName = "sessao_id_seq", allocationSize = 1)
    private Integer idSessao;

    private LocalDateTime inicio;

    private Integer duracao;
}
