package br.com.vitt.apivotacao.entities;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.vitt.apivotacao.entities.enums.StatusPauta;
import br.com.vitt.apivotacao.entities.enums.Voto;
@Entity
@Table(name="pauta_associado")
public class PautaAssociado implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@JsonIgnore
	@EmbeddedId
	private PautaAssociadoPK id = new PautaAssociadoPK();
	
	private Integer voto = 2;

	public PautaAssociado() {}

	public PautaAssociado(Associado associado, Pauta pauta) {
		id.setAssociado(associado);
		id.setPauta(pauta);
	}

	public PautaAssociadoPK getId() {
		return id;
	}

	public void setId(PautaAssociadoPK id) {
		this.id = id;
	}

	public Voto getVoto() {
		return Voto.toEnum(voto);
	}	

	public void setVoto(Voto voto) {
		if(getPauta().getFim().isAfter(LocalDateTime.now())
				&& getPauta().getStatusPauta() == StatusPauta.IN_VOTING) {
			this.voto = voto.getCod();			
		}
	};
		
	public Associado getAssociado() {
		return id.getAssociado();
	}
	
	@JsonIgnore
	public Pauta getPauta() {
		return id.getPauta();
	}
	
}
