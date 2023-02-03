package com.br.dbserver.votacao.model;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.br.dbserver.votacao.dto.DadosVoto;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "voto")
@Entity(name = "Voto")
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Voto implements Serializable {

    private static final long serialVersionUID = 1L;
	
	public Voto(@Valid DadosVoto dados, Pauta pauta, Associado associado) {
		this.tipoVoto = dados.tipoVoto(); 
		this.id.setAssociado(associado);
        this.id.setPauta(pauta);
		this.dataRegistroVoto = LocalDateTime.now();
	}


	private LocalDateTime dataRegistroVoto;
	private TipoVoto tipoVoto;

	@JsonIgnore
    @EmbeddedId
    private VotoPK id = new VotoPK();
	
    public Associado getAssociado() {
        return id.getAssociado();
    }
	
    public void setAssociado(Associado associado) {
        id.setAssociado(associado);
    }

    public Pauta getPauta() {
        return id.getPauta();
    }

    public void setPauta(Pauta pauta) {
        id.setPauta(pauta);
    }
	

}
