package br.com.dbserver.votacao.v1.service;


import br.com.dbserver.votacao.v1.dto.request.PautaRequest;
import br.com.dbserver.votacao.v1.dto.response.PautaResponse;
import br.com.dbserver.votacao.v1.dto.response.PautaResultadoResponse;

public interface PautaService {
	PautaResponse criarPauta(PautaRequest pautaRequest);

	PautaResultadoResponse buscarPorID(Long id);
}
