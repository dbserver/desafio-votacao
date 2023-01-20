package br.com.vitt.apivotacao.tests;

import java.time.LocalDateTime;

import br.com.vitt.apivotacao.dto.AssociadoDTO;
import br.com.vitt.apivotacao.dto.PautaDTO;
import br.com.vitt.apivotacao.entities.Associado;
import br.com.vitt.apivotacao.entities.Pauta;

public class Factory {
	
	public static Pauta createPauta() {

		Pauta pauta = new Pauta(1L, "Pauta teste", 1, LocalDateTime.now(), null, null, true);
		return pauta;
	}
	
	public static Pauta createPautaDois() {

		Pauta pauta = new Pauta(2L, "Pauta teste2", 1, LocalDateTime.now(), null, null, true);
		return pauta;
	}
	
	public static Pauta createPautaTres() {

		Pauta pauta = new Pauta(3L, "Pauta teste3", 1, LocalDateTime.now(), null, null, true);
		return pauta;
	}

	public static PautaDTO createPautaDto() {
		Pauta pauta = createPauta();
		return new PautaDTO(pauta);
	}
	
	public static Associado createAssociado() {

		Associado associado = new Associado(1L, "Associado teste", "789.994.300-06", 1, true);
		return associado;
	}
	
	public static Associado createAssociadoDois() {

		Associado associado = new Associado(2L, "Associado teste2", "247.564.120-76", 1, true);
		return associado;
	}
	
	public static Associado createAssociadoTres() {

		Associado associado = new Associado(3L, "Associado teste3", "296.428.900-03", 1, true);
		return associado;
	}

	public static AssociadoDTO createAssociadoDto() {
		Associado associado = createAssociado();
		return new AssociadoDTO(associado);
	}

}
