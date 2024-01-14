package com.cooperativa.votacao.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cooperativa.votacao.dto.AberturaSessaoDTO;
import com.cooperativa.votacao.entity.PautaEntity;
import com.cooperativa.votacao.entity.StatusSessaoEntity;
import com.cooperativa.votacao.entity.StatusSessaoEnum;
import com.cooperativa.votacao.exception.SessaoException;

@Service
@Transactional
public class SessaoServiceImpl implements SessaoService {
	
	@Autowired
	private PautaService pautaService;

	@Override
	public void abrirSessaoVotacao(AberturaSessaoDTO aberturaSessaoDTO) {
		

		 PautaEntity pautaEntity= pautaService.buscarPorId(aberturaSessaoDTO.getIdPauta());
			
		 this.validarSessao(pautaEntity.getStatusSessao(),StatusSessaoEnum.CRIADO);
		 pautaEntity.setStatusSessao(new StatusSessaoEntity(StatusSessaoEnum.ABERTO));
		 pautaEntity.setTempoSessao(aberturaSessaoDTO.getTempoSessao());
	
		 pautaService.atualizar(pautaEntity);
	}
	
	@Override
	public void validarSessao(StatusSessaoEntity statusSessaoEntity, StatusSessaoEnum statusSessaoValida) {
		if(!statusSessaoEntity.getNomeStatusSessao().equals(statusSessaoValida.name())) {
			
		  if(statusSessaoEntity.getNomeStatusSessao().equals(StatusSessaoEnum.CRIADO.name()))
			  throw new SessaoException("Sessão de votação ainda não foi aberta");
		  
		  if(statusSessaoEntity.getNomeStatusSessao().equals(StatusSessaoEnum.ABERTO.name()))
			  throw new SessaoException("Sessão de votação já foi aberta");
		  
		  else if(statusSessaoEntity.getNomeStatusSessao().equals(StatusSessaoEnum.FINALIZADO.name()))
			  throw new SessaoException("Sessão de votação já foi finalizada");
		}
			  
	}

}
