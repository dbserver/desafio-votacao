package com.cooperativa.votacao.service;

import com.cooperativa.votacao.dto.PautaDTO;
import com.cooperativa.votacao.entity.PautaEntity;

public interface PautaService {
	
	public PautaDTO cadastrar(PautaDTO pautaDTO);

	public PautaEntity buscarPorId(Integer id);
	
	public void atualizar(PautaEntity pautaEntity);
}
