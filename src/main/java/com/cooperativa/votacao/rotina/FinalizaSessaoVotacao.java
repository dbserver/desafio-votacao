package com.cooperativa.votacao.rotina;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.cooperativa.votacao.service.SessaoService;

@Component
public class FinalizaSessaoVotacao {
	
	@Autowired
	private SessaoService sessaoService;
	
	@Scheduled(fixedDelay =10000)
	public void finalizarSessaoVotacao() {
		sessaoService.finalizarSessao();
	}

}
