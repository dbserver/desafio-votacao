package com.br.dbserver.votacao.service;

import java.time.LocalDateTime;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.br.dbserver.votacao.controller.dto.DadosAbrirSessao;
import com.br.dbserver.votacao.dto.DadosCadastroPauta;
import com.br.dbserver.votacao.model.Pauta;
import com.br.dbserver.votacao.repository.PautaRepository;

import jakarta.validation.Valid;

@Service
public class PautaService {
	
	@Autowired
	private PautaRepository pautaRepository;
	
	public Pauta findPautaById(long id) {
		  return  pautaRepository.getReferenceById(id);
 
	}

	public Pauta salvarPauta(@Valid DadosCadastroPauta dados) {
		Pauta pauta = new Pauta(dados);
		return pautaRepository.save(pauta);		
	}

	public void abrirSessao(DadosAbrirSessao dados) {
		Pauta pauta = pautaRepository.getReferenceById(dados.pautaId());
		
		if(Objects.nonNull(pauta.getInicioVotacao())) {
			//Sessão aberta
		}
		if(Objects.nonNull(pauta.getInicioVotacao()) && pauta.getFimVotacao().isAfter(LocalDateTime.now())) {
			//Sessão finalizada
		}
		
		if(Objects.isNull(pauta.getInicioVotacao())) {
			pauta.setInicioVotacao(LocalDateTime.now());
			int tempoDaSessao = Objects.nonNull(dados.duracao()) ? dados.duracao() : 1;
			pauta.setFimVotacao(pauta.getInicioVotacao().plusMinutes(tempoDaSessao));
			pautaRepository.save(pauta);
		}
	}


	public void atualizarResultado(long id, String resultado) {
		pautaRepository.atualizarResultado(id, resultado);
	}
	
			

}
