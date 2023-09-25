package com.desafio.votacao.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.desafio.votacao.dto.request.VotacaoRequest;
import com.desafio.votacao.dto.response.MessageResponse;
import com.desafio.votacao.entity.Pauta;
import com.desafio.votacao.entity.Votacao;
import com.desafio.votacao.enums.PautaStatusEnum;
import com.desafio.votacao.exception.BusinessException;
import com.desafio.votacao.exception.NotFoundException;
import com.desafio.votacao.repository.VotacaoRepository;
import com.desafio.votacao.service.PautaService;
import com.desafio.votacao.service.VotacaoService;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
public class VotacaoServiceImpl implements VotacaoService {

	private VotacaoRepository votacaoRepository;

	private PautaService pautaService;

	@Autowired
	public VotacaoServiceImpl(VotacaoRepository votacaoRepository, PautaService pautaService) {
		this.votacaoRepository = votacaoRepository;
		this.pautaService = pautaService;
	}

	@Override
	public void fecharVotacao() {
		List<Votacao> list = this.votacaoRepository.findByAtivoExpirada();
		if (!list.isEmpty()) {
			try {
				this.votacaoRepository.inativarVotacaoVencida();
				for (Votacao votacao : list) {
					Long pautaId = votacao.getPauta().getId();
					this.pautaService.atualizarPautaVotacao(pautaId);
				}
			} catch (Exception e) {
				log.error("Erro ao atualizar Pauta: ", e.getLocalizedMessage());
			}
		}

	}

	@Override
	public ResponseEntity<MessageResponse> iniciarVotacao(VotacaoRequest request) {
		Optional<Pauta> optPauta = this.pautaService.buscarPorId(request.getPautaId());
		if(!optPauta.isPresent() ) {
			throw new NotFoundException("A pauta não existe!");
		}
		if (optPauta.get().getStatus().equals(PautaStatusEnum.AGUARDANDO_APURACAO) || !optPauta.get().isAtivo()) {
			throw new BusinessException("A pauta é invalida para iniciar uma votação!");
		}
		Pauta pauta = optPauta.get();
		Votacao votacao = new Votacao();
		votacao.setPauta(pauta);
		this.votacaoRepository.save(votacao);
		return ResponseEntity.ok(new MessageResponse("Votação incia com sucesso."));

	}

	@Override
	public boolean validarVotacaoAtiva(Pauta pauta) {
		Votacao votacao = this.votacaoRepository.findFirstByPauta(pauta).get();
		if(votacao.isAtivo()) {
			return true;
		}
		if(votacao.getDthFim().isBefore(LocalDateTime.now())) {
			return false;
		}
		return true;
	}
}
