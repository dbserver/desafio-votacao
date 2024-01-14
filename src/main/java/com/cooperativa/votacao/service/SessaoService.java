package com.cooperativa.votacao.service;

import com.cooperativa.votacao.dto.AberturaSessaoDTO;
import com.cooperativa.votacao.entity.StatusSessaoEntity;
import com.cooperativa.votacao.entity.StatusSessaoEnum;

public interface SessaoService {
	
	public void abrirSessaoVotacao(AberturaSessaoDTO aberturaSessaoDTO);
	
	public void validarSessao(StatusSessaoEntity statusSessaoEntity, StatusSessaoEnum statusSessaoValida);
	
	public void finalizarSessao();

}
