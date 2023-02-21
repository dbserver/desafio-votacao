package br.com.dbserver.votacao.v1.service;


import br.com.dbserver.votacao.v1.dto.request.PautaRequest;
import br.com.dbserver.votacao.v1.dto.response.PautaResponse;
import br.com.dbserver.votacao.v1.dto.response.PautaResultadoResponse;
import br.com.dbserver.votacao.v1.mapper.Resposta;
import org.springframework.data.domain.Pageable;

public interface PautaService {
	PautaResponse criarPauta(PautaRequest pautaRequest);

	PautaResultadoResponse buscarPorID(Long id);

	Resposta<PautaResponse> buscarTodas(Pageable pageable);
}
