package com.desafio.votacao.service;

import com.desafio.votacao.dto.VotoDTO;
import com.desafio.votacao.entity.Pauta;

public interface VotoService {

	public void salvar(VotoDTO dto);
	
	public Long contarVotos(boolean voto, Pauta pauta);
	
	
}
