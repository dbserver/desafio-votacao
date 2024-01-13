package com.cooperativa.votacao.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cooperativa.votacao.dto.AberturaSessaoDTO;
import com.cooperativa.votacao.entity.PautaEntity;
import com.cooperativa.votacao.entity.StatusSessaoEntity;
import com.cooperativa.votacao.entity.StatusSessaoEnum;
import com.cooperativa.votacao.exception.SessaoException;
import com.cooperativa.votacao.repository.PautaRepository;

@Service
@Transactional
public class SessaoServiceImpl implements SessaoService {
	
	@Autowired
	private PautaRepository pautaRepository;

	@Override
	public void abrirSessaoVotacao(AberturaSessaoDTO aberturaSessaoDTO) {
		

		 PautaEntity pautaEntity= pautaRepository.findById(aberturaSessaoDTO.getId()).orElse(null);
			
		 this.validarSessao(pautaEntity.getStatusSessao(),StatusSessaoEnum.CRIADO);
		 pautaEntity.setStatusSessao(new StatusSessaoEntity(StatusSessaoEnum.ABERTO));
		 pautaEntity.setTempoSessao(aberturaSessaoDTO.getTempoSessao());
	
		 pautaRepository.save(pautaEntity);
	}
	
	private void validarSessao(StatusSessaoEntity statusSessaoEntity, StatusSessaoEnum statusSessaoValida) {
		if(!statusSessaoEntity.getNomeStatusSessao().equals(statusSessaoValida.name())) {
			
		  if(statusSessaoEntity.getNomeStatusSessao().equals(StatusSessaoEnum.CRIADO.name()))
			  throw new SessaoException("Sessão de votação já foi criada");
		  
		  if(statusSessaoEntity.getNomeStatusSessao().equals(StatusSessaoEnum.ABERTO.name()))
			  throw new SessaoException("Sessão de votação já foi aberta");
		  
		  else if(statusSessaoEntity.getNomeStatusSessao().equals(StatusSessaoEnum.FINALIZADO.name()))
			  throw new SessaoException("Sessão de votação já foi finalizada");
		}
			  
	}

}
