package br.com.dbserver.votacao.v1.service;

import br.com.dbserver.votacao.v1.dto.request.AssembleiaRequest;
import br.com.dbserver.votacao.v1.dto.response.AssembleiaPaginadaResponse;
import br.com.dbserver.votacao.v1.dto.response.AssembleiaResponse;
import org.springframework.data.domain.Pageable;

public interface AssembleiaService {
	AssembleiaResponse criar(AssembleiaRequest assembleiaRequest);

	AssembleiaPaginadaResponse buscarTodas(Pageable pageable);
}
