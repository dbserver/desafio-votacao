package com.desafio.votacao.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.desafio.votacao.repository.PautaRepository;

@Service
public class PautaService {

	private PautaRepository pautaRepository;

	@Autowired
	public PautaService(PautaRepository pautaRepository) {
		this.pautaRepository = pautaRepository;
	}

	/**
	 * Método utilizado para desativar Pautas de votações vencidas através de uma lista de Long.
	 * @param pautaId Lista id de Pauta do tipo Long
	 */
	public void desativarPautas(List<Long> pautaId) {
		this.pautaRepository.inativarPautaVotacaoVencida(pautaId);
	}
	
	
}
