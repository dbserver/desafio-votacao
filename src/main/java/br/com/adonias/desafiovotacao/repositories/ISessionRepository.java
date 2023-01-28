package br.com.adonias.desafiovotacao.repositories;

import br.com.adonias.desafiovotacao.entities.Session;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ISessionRepository extends CrudRepository<Session, Long> {
    List<Session> findAll();
}
