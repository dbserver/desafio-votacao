package br.com.dbserver.votacao.v1.service;

import br.com.dbserver.votacao.v1.dto.request.AssociadoRequest;
import br.com.dbserver.votacao.v1.dto.response.AssociadoResponse;

public interface AssociadoService {

    AssociadoResponse salvar(AssociadoRequest associado);
}
