package com.cooperativa.votacao.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "PAUTA",schema = "VOTACAO")
public class PautaEntity {
	
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;
	
	private String nome;
	
	@ManyToOne
	@JoinColumn(name ="ID_STATUS_SESSAO")
	private StatusSessaoEntity statusSessao;
	
	private LocalDateTime tempoSessao;
	

	
	public PautaEntity() {
		super();
	}

	public PautaEntity(String nome, StatusSessaoEntity statusSessao) {
		this.nome = nome;
		this.statusSessao = statusSessao;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public StatusSessaoEntity getStatusSessao() {
		return statusSessao;
	}

	public void setStatusSessao(StatusSessaoEntity statusSessao) {
		this.statusSessao = statusSessao;
	}

	public LocalDateTime getTempoSessao() {
		return tempoSessao;
	}

	public void setTempoSessao(LocalDateTime tempoSessao) {
		this.tempoSessao = tempoSessao;
	}
	
	

}
