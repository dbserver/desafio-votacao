package com.desafio.votacao.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.desafio.votacao.dto.PautaDTO;
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
	public void salvar(PautaDTO dto) {
		Pauta pauta = new Pauta(dto);
		this.pautaRepository.save(pauta);
	} 

	@Override
	public void salvarVotos(Long votosSim, Long votosNao, Long pautaId) {
		Optional<Pauta> pauta =this.pautaRepository.findById(pautaId);
		if(pauta.isPresent()) {
			Pauta p = pauta.get();
			p.setQtdVotosNao(votosNao);
			p.setQtdVotosSim(votosSim);
			p.setStatus(this.atualizarStatus(p));
			p.setAtivo(false);
			this.pautaRepository.save(p);			
		}
		
	}
	
	private PautaStatusEnum atualizarStatus(Pauta  p) {
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
