package com.cooperativa.votacao.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "VOTO",schema = "VOTACAO")
public class VotoEntity {
	
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
	
	@ManyToOne
	@JoinColumn(name ="ID_PAUTA")
	private PautaEntity pauta;
	
	private Integer idAssociado;
	
	private String voto;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public PautaEntity getPauta() {
		return pauta;
	}

	public void setPauta(PautaEntity pauta) {
		this.pauta = pauta;
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
