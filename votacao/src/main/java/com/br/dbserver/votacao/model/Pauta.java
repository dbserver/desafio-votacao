package com.br.dbserver.votacao.model;

import java.time.LocalDateTime;
import java.util.List;

import com.br.dbserver.votacao.dto.DadosCadastroPauta;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "pauta")
@Entity(name = "Pauta")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Pauta {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String tema; 
	
	@Column(name = "inicio_votacao")
	private LocalDateTime inicioVotacao;

	@Column(name = "fim_votacao")
	private LocalDateTime fimVotacao;
	
	private String resultadoVotacao;
	
	@OneToMany(mappedBy = "id.pauta")
	private List<Voto> listaVotos;
	
	

	public Pauta(String tema, LocalDateTime inicioVotacao, LocalDateTime fimVotacao) {
		super();
		this.tema = tema;
		this.inicioVotacao = inicioVotacao;
		this.fimVotacao = fimVotacao;
	}

	public Pauta(@Valid DadosCadastroPauta dados) {
		this.tema = dados.tema();
	}
	
}
