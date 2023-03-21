package com.desafio.votacao.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "TB_PAUTA")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@SequenceGenerator(name = "TB_PAUTA_SEQ", sequenceName = "TB_PAUTA_SEQ", allocationSize = 1)
public class Pauta implements Serializable {

	private static final long serialVersionUID = -8221106341995879973L;

	@Id
	@Column(name = "ID_PAUTA")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TB_PAUTA_SEQ")
	private Long id;

	@Column(name = "DS_PAUTA", nullable = false)
    private String descricao;

    @Column(name = "DT_INICIO", nullable = false)
    private LocalDateTime dtInicio;

    @Column(name = "DT_FIM", nullable = false)
    private LocalDateTime dtFim;

	@Column(name = "FL_EXCLUIDA", nullable = false)
    private boolean flExcluida;

	@Column(name = "FL_FINALIZADA", nullable = false)
    private boolean flFinalizada;
}
