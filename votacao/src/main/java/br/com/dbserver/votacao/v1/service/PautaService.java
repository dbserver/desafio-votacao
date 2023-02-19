package br.com.dbserver.votacao.v1.service;


import br.com.dbserver.votacao.v1.dto.request.PautaRequest;
import br.com.dbserver.votacao.v1.dto.response.PautaPaginadaResponse;
import br.com.dbserver.votacao.v1.dto.response.PautaResponse;
import br.com.dbserver.votacao.v1.dto.response.PautaResultadoResponse;
import org.springframework.data.domain.Pageable;

public interface PautaService {
	PautaResponse criarPauta(PautaRequest pautaRequest);

	PautaResultadoResponse buscarPorID(Long id);

	PautaPaginadaResponse buscarTodas(Pageable pageable);
}
