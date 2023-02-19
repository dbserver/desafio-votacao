package br.com.dbserver.votacao.v1.service;

import br.com.dbserver.votacao.v1.dto.request.AssembleiaRequest;
import br.com.dbserver.votacao.v1.dto.response.AssembleiaResponse;

public interface AssembleiaService {
	AssembleiaResponse criar(AssembleiaRequest assembleiaRequest);
}
