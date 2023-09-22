package com.desafio.votacao.schedule;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import com.desafio.votacao.service.VotacaoService;

import lombok.extern.slf4j.Slf4j;
@Slf4j
@Configuration
@EnableScheduling
public class FecharVotacaoSchedule {

	private VotacaoService votacaoService;

	@Autowired
	public FecharVotacaoSchedule(VotacaoService votacaoService) {
		this.votacaoService = votacaoService;
	}
 
	/**
	 * Método utilizado para Verificar e desativar Pautas e Votações expiradas.
	 * 
	 * fixedDelayString está configurado com 60000 millisegundos que é correspondente a 1 minuto 
	 */
	@Scheduled(fixedDelayString = "60000")
	private void fecharVotacao() {
		log.info("Inicio Rotina para Inativar Votação expirada.");
		this.votacaoService.fecharVotacao();
		log.info("Final da Rotina para Inativar Votação expirada.");
	}
}
