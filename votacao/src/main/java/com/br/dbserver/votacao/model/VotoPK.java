package com.br.dbserver.votacao.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
@EqualsAndHashCode(of = {"associado", "pauta"})
public class VotoPK implements Serializable {

  private static final long serialVersionUID = 1L;
	
	@ManyToOne
	@JoinColumn(name="associado_id", nullable = false)
	private Associado associado;
	
	@ManyToOne
	@JoinColumn(name="pauta_id", nullable=false)
	private Pauta pauta;
		
	}