
package com.dbserver.desafiovotacao.repository;

import com.dbserver.desafiovotacao.model.Pauta;
import java.util.Optional;
import java.util.UUID;
import org.springframework.dao.DataAccessException;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface PautaRepositorio extends CrudRepository<Pauta, UUID> {

    @Override
    Optional<Pauta> findById(UUID id) throws DataAccessException;
    Optional<Pauta> findByHash(String hash) throws DataAccessException;
    Pauta save(Pauta pauta);

}
