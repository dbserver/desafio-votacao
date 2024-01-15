package com.cooperativa.votacao.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
	
    private static Logger log = LoggerFactory.getLogger(PautaServiceImpl.class);
	
	@Autowired
	private PautaRepository pautaRepository;
	
	@Autowired
	private ContablizacaoVotacaoRepository contablizacaoVotacaoRepository;
	
	@Autowired
	private PautaMapper pautaMapper;

	@Override
	public PautaDTO cadastrar(PautaDTO pautaDTO) {
		log.info("Cadastrando pauta "+pautaDTO.getNome());
		
		return pautaMapper.entityToDTO(pautaRepository.save(new PautaEntity(pautaDTO.getNome(), 
				new StatusSessaoEntity(StatusSessaoEnum.CRIADO))));
	}

	@Override
	public PautaEntity buscarPorId(Integer id) {
		log.info("Consultando idPauta "+ id);
		
		return pautaRepository.findById(id).
				orElseThrow(()-> new PautaNaoEncontradaException("Pauta não encontrada"));
	}

	@Override
	public void atualizar(PautaEntity pautaEntity) {
		log.info("Atualizando  pauta idPauta "+pautaEntity.getId());
		
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
		log.info("Buscando status sessao "+statusSessaoEntity.getNomeStatusSessao());
		
		return pautaRepository.findByStatusSessao(statusSessaoEntity);
	}

	
}
