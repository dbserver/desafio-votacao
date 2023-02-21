package br.com.dbserver.votacao.v1.service;

import br.com.dbserver.votacao.v1.dto.request.VotoRequest;
import br.com.dbserver.votacao.v1.dto.response.VotoResponse;

public interface VotoService {
	VotoResponse salvar(VotoRequest votoRequest);
}
