package com.dbserver.desafio.valida.cpf.repository.entity;

import com.dbserver.desafio.valida.cpf.usecase.enuns.VotoEnum;
import lombok.Data;
import lombok.experimental.FieldNameConstants;

import javax.persistence.*;

@Entity
@Table(name = "voto")
@Data
@FieldNameConstants
public class VotoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "voto_id_generator")
    @SequenceGenerator(name = "voto_id_generator", sequenceName = "sessao_id_seq", allocationSize = 1)
    private Integer idVoto;

    private String cpfAssociado;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "idPauta")
    private PautaEntity pauta;

    private VotoEnum voto;
}
