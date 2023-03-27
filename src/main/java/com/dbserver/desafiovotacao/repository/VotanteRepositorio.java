
package com.dbserver.desafiovotacao.repository;


import com.dbserver.desafiovotacao.model.Votante;

import java.util.Optional;
import java.util.UUID;

import org.springframework.dao.DataAccessException;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface VotanteRepositorio extends CrudRepository<Votante, UUID> {
    @Override
    Optional<Votante> findById(UUID id) throws DataAccessException;
    Votante save(Votante votante);
}
