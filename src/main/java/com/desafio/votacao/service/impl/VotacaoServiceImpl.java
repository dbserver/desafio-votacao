package com.desafio.votacao.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.desafio.votacao.entity.Votacao;
import com.desafio.votacao.repository.VotacaoRepository;
import com.desafio.votacao.service.PautaService;
import com.desafio.votacao.service.VotoService;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
public class VotacaoServiceImpl {

	private VotacaoRepository votacaoRepository;

	private PautaService pautaService;

	private VotoService votoSerivice;

	@Autowired
	public VotacaoServiceImpl(VotacaoRepository votacaoRepository, PautaService pautaService,
			VotoService votoSerivice) {
		this.votacaoRepository = votacaoRepository;
		this.pautaService = pautaService;
		this.votoSerivice = votoSerivice;
	}

	public void fecharVotacao() {
		List<Votacao> list = this.votacaoRepository.findByAtivoExpirada();
		if (!list.isEmpty()) {
			try {
				this.votacaoRepository.inativarVotacaoVencida();
				for (Votacao votacao : list) {
					Long pautaId = votacao.getPauta().getId();
					Long votosSim = this.votoSerivice.contarVotos(true, votacao.getPauta());
					Long votosNao = this.votoSerivice.contarVotos(false, votacao.getPauta());
					this.pautaService.salvarVotos(votosSim, votosNao, pautaId);
				}
			} catch (Exception e) {
				log.error("Erro ao salvar votos em Pautas: ", e.getLocalizedMessage());
			}
		}

	}
}
