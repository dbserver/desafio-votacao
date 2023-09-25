package com.desafio.votacao.service;

import org.springframework.http.ResponseEntity;

import com.desafio.votacao.dto.request.VotoRequest;
import com.desafio.votacao.dto.response.MessageResponse;
import com.desafio.votacao.entity.Pauta;

public interface VotoService {

	public ResponseEntity<MessageResponse> salvar(VotoRequest request);
	
	public Long contarVotos(boolean voto, Pauta pauta);
	

	/**
	 * Método utilizado para iniciar a apuração dos votos para pautas com o Status de AGUARDANDO_APURACAO.
	 * Método chamado atraves do Scheduler 'CalcularVotosSchedule'
	 */
	public void iniciarApuracaoDeVotos();
}
