
package com.dbserver.desafiovotacao.service;

import com.dbserver.desafiovotacao.dto.AssembleiaRequest;
import com.dbserver.desafiovotacao.dto.AssembleiaResponse;
import com.dbserver.desafiovotacao.dto.ClienteRequest;
import com.dbserver.desafiovotacao.model.Assembleia;
import com.dbserver.desafiovotacao.model.Pauta;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.dao.DataAccessException;


public interface AssembleiaService {
    Optional<Assembleia> encontrarAssembleiaPorID(UUID id) throws DataAccessException;
    Assembleia salvarAssembleia(AssembleiaRequest assembleiaRequest) throws DataAccessException;
    Integer totalPautas(UUID id);
    Iterable<Assembleia> mostraTudo();
    List<AssembleiaResponse> mostrarAssembleias();
    Iterable<Pauta> mostraPautas(UUID id);
    Assembleia adicionarPauta(UUID idAssembleia, ClienteRequest clienteRequest);
    Assembleia finalizarAssembleia(UUID id);
}
