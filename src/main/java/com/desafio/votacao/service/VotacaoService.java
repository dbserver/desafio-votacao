package com.desafio.votacao.service;

import org.springframework.http.ResponseEntity;

import com.desafio.votacao.dto.request.VotacaoRequest;
import com.desafio.votacao.dto.response.MessageResponse;
import com.desafio.votacao.entity.Pauta;

public interface VotacaoService {
 

	public void fecharVotacao();

	public ResponseEntity<MessageResponse> iniciarVotacao(VotacaoRequest request);
	
	public boolean validarVotacaoAtiva(Pauta pauta);
	
}
