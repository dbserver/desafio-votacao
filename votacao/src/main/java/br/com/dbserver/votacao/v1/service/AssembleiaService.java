package br.com.dbserver.votacao.v1.service;

import br.com.dbserver.votacao.v1.dto.request.AssembleiaRequest;
import br.com.dbserver.votacao.v1.dto.response.AssembleiaPaginadaResponse;
import br.com.dbserver.votacao.v1.dto.response.AssembleiaResponse;
import br.com.dbserver.votacao.v1.mapper.Resposta;
import org.springframework.data.domain.Pageable;

public interface AssembleiaService {
	AssembleiaResponse criar(AssembleiaRequest assembleiaRequest);

	Resposta<AssembleiaResponse> buscarTodas(Pageable pageable);
}
