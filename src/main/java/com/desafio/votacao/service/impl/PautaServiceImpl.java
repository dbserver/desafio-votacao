package com.desafio.votacao.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.desafio.votacao.dto.response.PautaDTO;
import com.desafio.votacao.entity.Pauta;
import com.desafio.votacao.enums.PautaStatusEnum;
import com.desafio.votacao.repository.PautaRepository;
import com.desafio.votacao.service.PautaService;

@Service
public class PautaServiceImpl implements PautaService {

	private PautaRepository pautaRepository;

	@Autowired
	public PautaServiceImpl(PautaRepository pautaRepository) {
		this.pautaRepository = pautaRepository;
	}

	@Override
	public ResponseEntity<PautaDTO> salvar(PautaDTO dto) {
		Pauta pauta = new Pauta(dto);
		PautaDTO retorno = new PautaDTO(this.pautaRepository.save(pauta));
		return ResponseEntity.ok(retorno);
	}

	@Override
	public Optional<Pauta> buscarPorId(Long pautaId) {
		return this.pautaRepository.findById(pautaId);
	}

	@Override
	public void atualizarPautaVotacao(Long pautaId) {
		Pauta pauta = this.buscarPorId(pautaId).get();
		pauta.setStatus(PautaStatusEnum.AGUARDANDO_APURACAO);
		this.pautaRepository.save(pauta);
	}

	
	@Override
	public List<Pauta> listarPautaAguardandoApuracao() {
		return this.pautaRepository.findByStatus(PautaStatusEnum.AGUARDANDO_APURACAO);
	}

	@Override
	public void salvarVotos(Long votosSim, Long votosNao, Pauta p) {
		p.setQtdVotosNao(votosNao);
		p.setQtdVotosSim(votosSim);
		p.setStatus(this.atualizarStatus(p));
		p.setAtivo(false);
		this.pautaRepository.save(p);
	}

	public PautaStatusEnum atualizarStatus(Pauta p) {
		long votosSim = p.getQtdVotosSim();
		long votosNao = p.getQtdVotosNao();
		if (votosSim == votosNao) {
			return PautaStatusEnum.EMPATADA;
		} else if (votosSim > votosNao) {
			return PautaStatusEnum.APROVADA;
		} else {
			return PautaStatusEnum.REPROVADA;
		}

	}

}
