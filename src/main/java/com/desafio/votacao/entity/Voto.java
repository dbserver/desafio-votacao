package com.desafio.votacao.entity;

import java.time.LocalDateTime;

import com.desafio.votacao.dto.response.VotoDTO;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name="voto", uniqueConstraints={@UniqueConstraint(columnNames = {"associado_id" , "pauta_id"})})
@NoArgsConstructor
public class Voto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	/**
	 * Coluna que representa a Data e hora do voto.
	 */
	@Basic(optional = false)
	@Column(name = "dth_criacao", nullable = false)
	private LocalDateTime dthCriacao = LocalDateTime.now();

	/**
	 * Coluna que representa o voto com os valores de 0 = 'Não' e 1 = 'Sim'.
	 */
	@Basic(optional = false)
	@Column(name = "voto", nullable = false)
	private boolean voto;

	@ManyToOne()
    @JoinColumn(name = "pauta_id", nullable = false, referencedColumnName = "id", unique = false)
	private Pauta pauta;

	@OneToOne()
    @JoinColumn(name = "associado_id", nullable = false, referencedColumnName = "id", unique = false)
	private Associado associado;
	
	public Voto(VotoDTO dto) {
		this.id = dto.getId(); 
		this.dthCriacao = dto.getDthCriacao();		
		this.voto = dto.isVoto();
		this.pauta = new Pauta(dto.getPauta());
		this.associado = new Associado(dto.getAssociado());
	}

	public Voto(Long id, LocalDateTime dthCriacao, boolean voto, Pauta pauta, Associado associado) {
		this.id = id;
		this.dthCriacao = dthCriacao;
		this.voto = voto;
		this.pauta = pauta;
		this.associado = associado;
	}

	
	
}
