package com.cooperativa.votacao.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cooperativa.votacao.dto.PautaDTO;
import com.cooperativa.votacao.entity.ContabilizacaoVotacaoPauta;
import com.cooperativa.votacao.entity.PautaEntity;
import com.cooperativa.votacao.entity.StatusSessaoEntity;
import com.cooperativa.votacao.entity.StatusSessaoEnum;
import com.cooperativa.votacao.exception.PautaNaoEncontradaException;
import com.cooperativa.votacao.mapper.PautaMapper;
import com.cooperativa.votacao.repository.ContablizacaoVotacaoRepository;
import com.cooperativa.votacao.repository.PautaRepository;

@Service
@Transactional
public class PautaServiceImpl implements PautaService {
	
	@Autowired
	private PautaRepository pautaRepository;
	
	@Autowired
	private ContablizacaoVotacaoRepository contablizacaoVotacaoRepository;
	
	@Autowired
	private PautaMapper pautaMapper;

	@Override
	public PautaDTO cadastrar(PautaDTO pautaDTO) {
		return pautaMapper.entityToDTO(pautaRepository.save(new PautaEntity(pautaDTO.getNome(), 
				new StatusSessaoEntity(StatusSessaoEnum.CRIADO))));
	}

	@Override
	public PautaEntity buscarPorId(Integer id) {
		return pautaRepository.findById(id).
				orElseThrow(()-> new PautaNaoEncontradaException("Pauta não encontrada"));
	}

	@Override
	public void atualizar(PautaEntity pautaEntity) {
		pautaRepository.save(pautaEntity);
	}

	@Override
	public ContabilizacaoVotacaoPauta buscarContabilizacaoVotacaoPorPauta(Integer idPauta) {
		//verifica se a pauta existe
		this.buscarPorId(idPauta);
		
		ContabilizacaoVotacaoPauta contabilizacaoVotacaoPauta = contablizacaoVotacaoRepository.
				buscarContablizacaoVotacaoPorPauta(idPauta);
		
		contabilizacaoVotacaoPauta.setTotal(contabilizacaoVotacaoPauta.getTotalSim()+contabilizacaoVotacaoPauta.getTotalNao());
		return contabilizacaoVotacaoPauta;
	}

	@Override
	public List<PautaEntity> buscarPorStatusSessao(StatusSessaoEntity statusSessaoEntity) {
		return pautaRepository.findByStatusSessao(statusSessaoEntity);
	}

	
}
