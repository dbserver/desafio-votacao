package com.desafio.votacao.schedule;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import com.desafio.votacao.service.VotoService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
@EnableScheduling
public class CalcularVotosSchedule {
	
	private VotoService votoService;
	
	@Autowired
	public CalcularVotosSchedule(VotoService votoService) {
		this.votoService = votoService;
	}


	/**
	 * Método utilizado para Verificar e Pautas que estão Aguardando apuração de votos para realizar a contagem.
	 * 
	 * fixedDelayString está configurado com 60000 millisegundos que é correspondente a 1 minuto 
	 */
	@Scheduled(fixedDelayString = "60000")
	private void calcularVotos() {
		log.info("Inicio Rotina para a apuração de votos.");
		this.votoService.iniciarApuracaoDeVotos();
		log.info("Final da Rotina para a apuração de votos.");
	}
}
