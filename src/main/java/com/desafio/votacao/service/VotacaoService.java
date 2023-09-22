package com.desafio.votacao.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.desafio.votacao.entity.Votacao;
import com.desafio.votacao.repository.VotacaoRepository;

import lombok.extern.slf4j.Slf4j;
@Slf4j
@Service
public class VotacaoService {

	private VotacaoRepository votacaoRepository;

	private PautaService pautaService;

	@Autowired
	public VotacaoService(VotacaoRepository votacaoRepository, PautaService pautaService) {
		this.votacaoRepository = votacaoRepository;
		this.pautaService = pautaService;
	}
 

	public void fecharVotacao() {
		List<Votacao> list = this.votacaoRepository.findByAtivoExpirada();
		
		if(!list.isEmpty()) {
			List<Long> pautaId = new ArrayList<>();
			for (Votacao votacao : list) {
					pautaId.add(votacao.getPauta().getId());
			}
			try {
				if(!pautaId.isEmpty()) {
					this.pautaService.desativarPautas(pautaId);
				}				
			} catch (Exception e) {
				log.error("Erro ao Desativar Pautas: ", e.getLocalizedMessage());
			} finally {
				this.votacaoRepository.inativarVotacaoVencida();
			}
		}
		
	}
	
	
}
