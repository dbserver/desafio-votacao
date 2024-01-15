package com.cooperativa.votacao.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Transient;

@Entity
public class ContabilizacaoVotacaoPauta {
	
	@Id
	private Integer idPauta;
	
	private String nomePauta;
	
	private LocalDateTime tempoSessao;
	
	private String statusSessao;
	
	private Integer totalSim;
	
	private Integer totalNao;
	
	@Transient
	private Integer total;

	public Integer getIdPauta() {
		return idPauta;
	}

	public void setIdPauta(Integer idPauta) {
		this.idPauta = idPauta;
	}

	public String getNomePauta() {
		return nomePauta;
	}

	public void setNomePauta(String nomePauta) {
		this.nomePauta = nomePauta;
	}

	public LocalDateTime getTempoSessao() {
		return tempoSessao;
	}

	public void setTempoSessao(LocalDateTime tempoSessao) {
		this.tempoSessao = tempoSessao;
	}

	public String getStatusSessao() {
		return statusSessao;
	}

	public void setStatusSessao(String statusSessao) {
		this.statusSessao = statusSessao;
	}

	public Integer getTotalSim() {
		return totalSim;
	}

	public void setTotalSim(Integer totalSim) {
		this.totalSim = totalSim;
	}

	public Integer getTotalNao() {
		return totalNao;
	}

	public void setTotalNao(Integer totalNao) {
		this.totalNao = totalNao;
	}

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}
	
	
	

	
	
	

}
