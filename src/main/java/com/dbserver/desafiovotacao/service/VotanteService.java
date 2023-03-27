
package com.dbserver.desafiovotacao.service;

import com.dbserver.desafiovotacao.dto.VotanteRequest;
import com.dbserver.desafiovotacao.model.Votante;
import java.util.Optional;
import java.util.UUID;
import org.springframework.dao.DataAccessException;

public interface VotanteService {
    Optional<Votante> encontrarVotantePorID(UUID id) throws DataAccessException;
	long totalVotantes();
	Iterable<Votante> findAll();
    Votante salvarVotante(VotanteRequest votanteRequest) throws DataAccessException;
}
