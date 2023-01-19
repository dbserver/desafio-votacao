package br.com.vitt.apivotacao.dto;

import java.io.Serializable;
import java.time.LocalDateTime;

import br.com.vitt.apivotacao.entities.Pauta;

public class PautaDTO implements Serializable{
	
	private static final long serialVersionUID = 1L; 

	private Long id;
	private String titulo;
	private Integer status;
	private LocalDateTime data = LocalDateTime.now();
	private LocalDateTime inicio;
	private LocalDateTime fim;
	private boolean ativo;
	
	public PautaDTO() {}

	public PautaDTO(Long id, String titulo, Integer status, LocalDateTime data, LocalDateTime inicio, LocalDateTime fim,
			boolean ativo) {
		this.id = id;
		this.titulo = titulo;
		this.status = status;
		this.data = data;
		this.inicio = inicio;
		this.fim = fim;
		this.ativo = ativo;
	}
	
	public PautaDTO(Pauta entity) {
		this.id =  entity.getId();
		this.titulo =  entity.getTitulo();
		this.status =  entity.getStatus();
		this.data =  entity.getData();
		this.inicio =  entity.getInicio();
		this.fim =  entity.getFim();
		this.ativo =  entity.getAtivo();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public LocalDateTime getData() {
		return data;
	}

	public void setData(LocalDateTime data) {
		this.data = data;
	}

	public LocalDateTime getInicio() {
		return inicio;
	}

	public void setInicio(LocalDateTime inicio) {
		this.inicio = inicio;
	}

	public LocalDateTime getFim() {
		return fim;
	}

	public void setFim(LocalDateTime fim) {
		this.fim = fim;
	}

	public boolean getAtivo() {
		return ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}
	
}
