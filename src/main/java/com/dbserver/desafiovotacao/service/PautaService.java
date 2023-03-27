
package com.dbserver.desafiovotacao.service;

import com.dbserver.desafiovotacao.dto.ClienteRequest;
import com.dbserver.desafiovotacao.dto.PautaRequest;
import com.dbserver.desafiovotacao.model.Pauta;
import java.util.Optional;
import java.util.UUID;
import org.springframework.dao.DataAccessException;

public interface PautaService {
    Optional<Pauta> encontrarPautaPorID(UUID id) throws DataAccessException;
    Optional<Pauta> encontrarPautaPorHash(String hash) throws DataAccessException;
    Pauta salvarPauta(PautaRequest pautaRequest) throws DataAccessException;
    Integer totalVotantes(UUID id);
    Iterable<Pauta> mostraPautas();
    Pauta adicionarAssociado(String hashPauta, ClienteRequest clienteRequest);
    Pauta finalizarPauta(String hash);
}
