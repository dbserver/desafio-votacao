package com.desafio.votacao.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.desafio.votacao.dto.request.VotoRequest;
import com.desafio.votacao.dto.response.MessageResponse;
import com.desafio.votacao.entity.Pauta;
import com.desafio.votacao.entity.Voto;
import com.desafio.votacao.enums.PautaStatusEnum;
import com.desafio.votacao.exception.BusinessException;
import com.desafio.votacao.exception.NotFoundException;
import com.desafio.votacao.repository.VotoRepository;
import com.desafio.votacao.service.AssociadoService;
import com.desafio.votacao.service.PautaService;
import com.desafio.votacao.service.VotacaoService;
import com.desafio.votacao.service.VotoService;

@Service
public class VotoServiceImpl implements VotoService {

	private VotoRepository votoRepository;
	private PautaService pautaService;
	private AssociadoService associadoService;
	private VotacaoService votacaoService;
	
	@Autowired 
	public VotoServiceImpl(VotoRepository votoRepository, PautaService pautaService,
			AssociadoService associadoService, VotacaoService votacaoService) {
		this.votoRepository = votoRepository;
		this.pautaService = pautaService;
		this.associadoService = associadoService;
		this.votacaoService = votacaoService;
	}

	@Override
	public ResponseEntity<MessageResponse> salvar(VotoRequest request) {
		Voto voto = new Voto();
		Optional<Pauta> optPauta = this.pautaService.buscarPorId(request.getPautaId());
		if(!this.votacaoService.validarVotacaoAtiva(optPauta.get())) {
			throw new BusinessException("Votação finalizada!"); 
		}
		if(!optPauta.isPresent() ) {
			throw new NotFoundException("A pauta não existe!");
		}
		
		if (!optPauta.get().getStatus().equals(PautaStatusEnum.AGUARDANDO_RESULTADO) || !optPauta.get().isAtivo()) {
			throw new BusinessException("A pauta é invalida para votação!");
		}
		voto.setPauta(optPauta.get());
		voto.setAssociado(this.associadoService.buscarPorCPF(request.getCpf()));
		voto.setVoto(request.getValor().getVoto());
		this.votoRepository.save(voto);
		return ResponseEntity.ok(new MessageResponse("Voto Realizado com Sucesso!"));
	}

	@Override
	public void iniciarApuracaoDeVotos() {
		List<Pauta> pautas = this.pautaService.listarPautaAguardandoApuracao();
		for (Pauta pauta : pautas) {
			Long votosSim = this.contarVotos(true, pauta);
			Long votosNao = this.contarVotos(false, pauta);
			this.pautaService.salvarVotos(votosSim, votosNao, pauta);
		}
	}
	
	@Override
	public Long contarVotos(boolean voto, Pauta pauta) {
		return this.votoRepository.countByVotoAndPauta(voto, pauta);
	}

}
