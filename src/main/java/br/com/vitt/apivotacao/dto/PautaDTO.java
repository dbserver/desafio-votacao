package br.com.vitt.apivotacao.dto;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.vitt.apivotacao.entities.Pauta;
import br.com.vitt.apivotacao.entities.enums.StatusPauta;

public class PautaDTO implements Serializable{
	
	private static final long serialVersionUID = 1L; 

	private Long id;
	private String titulo;
	private Integer statusPauta = 1;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime data = LocalDateTime.now();

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime inicio;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime fim;
	private boolean ativo;
	
	public PautaDTO() {}

	public PautaDTO(Long id, String titulo, Integer status, LocalDateTime inicio, LocalDateTime fim,
			boolean ativo) {
		this.id = id;
		this.titulo = titulo;
		this.statusPauta = status;
		this.inicio = inicio;
		this.fim = fim;
		this.ativo = ativo;
	}
	
	public PautaDTO(Pauta entity) {
		this.id =  entity.getId();
		this.titulo =  entity.getTitulo();
		this.statusPauta =  entity.getStatusPauta().getCod();
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

	public StatusPauta getStatusPauta() {		
		return StatusPauta.toEnum(statusPauta);
	}
	
	public void setStatusPauta(StatusPauta statusPauta) {
		this.statusPauta = statusPauta.getCod();
	}

	public LocalDateTime getInicio() {
		return inicio;
	}
	
	public LocalDateTime getData() {
		return data;
	}

	public void setData(LocalDateTime data) {
		this.data = data;
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
