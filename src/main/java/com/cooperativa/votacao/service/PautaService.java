package com.cooperativa.votacao.service;

import java.util.List;

import com.cooperativa.votacao.dto.PautaDTO;
import com.cooperativa.votacao.entity.ContabilizacaoVotacaoPauta;
import com.cooperativa.votacao.entity.PautaEntity;
import com.cooperativa.votacao.entity.StatusSessaoEntity;

public interface PautaService {
	
	public PautaDTO cadastrar(PautaDTO pautaDTO);

	public PautaEntity buscarPorId(Integer id);
	
	public void atualizar(PautaEntity pautaEntity);
	
	public ContabilizacaoVotacaoPauta buscarContabilizacaoVotacaoPorPauta(Integer idPauta);
	
	public List<PautaEntity> buscarPorStatusSessao(StatusSessaoEntity statusSessaoEntity);
}
