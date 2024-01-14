package com.cooperativa.votacao.service;

import java.time.LocalDateTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
	
	private static Logger log = LoggerFactory.getLogger(SessaoServiceImpl.class);
	
	@Autowired
	private PautaService pautaService;

	@Override
	public void abrirSessaoVotacao(AberturaSessaoDTO aberturaSessaoDTO) {
		 log.info("abrindo sessao");

		 PautaEntity pautaEntity= pautaService.buscarPorId(aberturaSessaoDTO.getIdPauta());
			
		 this.validarSessao(pautaEntity.getStatusSessao(),StatusSessaoEnum.CRIADO);
		 pautaEntity.setStatusSessao(new StatusSessaoEntity(StatusSessaoEnum.ABERTO));
		 
		 if(aberturaSessaoDTO.getTempoSessao()==null)
			pautaEntity.setTempoSessao(LocalDateTime.now().plusMinutes(1));
		 else 
		    pautaEntity.setTempoSessao(aberturaSessaoDTO.getTempoSessao());
	
		 pautaService.atualizar(pautaEntity);
	}
	
	@Override
	public void finalizarSessao() {
		 pautaService.buscarPorStatusSessao
				(new StatusSessaoEntity(StatusSessaoEnum.ABERTO)).stream().filter(e->this.verificaSessaoExpirada(e)).forEach(e->{
					e.setStatusSessao(new StatusSessaoEntity(StatusSessaoEnum.FINALIZADO));
					pautaService.atualizar(e);
				});
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

	private boolean verificaSessaoExpirada(PautaEntity pautaEntity) {
		return LocalDateTime.now().isAfter(pautaEntity.getTempoSessao());
	}
	

	

}
