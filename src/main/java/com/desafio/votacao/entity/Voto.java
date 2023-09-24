package com.desafio.votacao.entity;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import com.desafio.votacao.dto.VotoDTO;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.Data;

@Data
@Entity
public class Voto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Basic(optional = false)
	@Column(name = "dth_criacao", nullable = false, columnDefinition="Coluna que representa a Data e hora do voto.")
	@CreationTimestamp
	private LocalDateTime dthCriacao;

	@Basic(optional = false)
	@Column(name = "voto", nullable = false, columnDefinition="Coluna que representa o voto com os valores de 0 = 'Não' e 1 = 'Sim'.")
	private boolean voto;

	@ManyToOne
	private Pauta pauta;

	@OneToOne
	private Associado associado;
	
	public Voto(VotoDTO dto) {
		this.id = dto.getId(); 
		this.dthCriacao = dto.getDthCriacao();		
		this.voto = dto.isVoto();
		this.pauta = new Pauta(dto.getPauta());
		this.associado = new Associado(dto.getAssociado());
	}

	
}
