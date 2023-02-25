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
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;

    private LocalDateTime inicio;

    private Integer duracao;
}
