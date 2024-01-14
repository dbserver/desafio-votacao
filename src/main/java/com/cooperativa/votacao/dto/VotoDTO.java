package com.cooperativa.votacao.dto;



import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class VotoDTO {
	
	@NotNull
	private Integer idPauta;
	
	@NotNull
	private Integer idAssociado;
	
	@NotBlank
	private String voto;

	public Integer getIdPauta() {
		return idPauta;
	}

	public void setIdPauta(Integer idPauta) {
		this.idPauta = idPauta;
	}

	public Integer getIdAssociado() {
		return idAssociado;
	}

	public void setIdAssociado(Integer idAssociado) {
		this.idAssociado = idAssociado;
	}

	public String getVoto() {
		return voto;
	}

	public void setVoto(String voto) {
		this.voto = voto;
	}

	
	
	

}
