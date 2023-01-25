package br.tec.db.desafiovotacao.repositories;

import br.tec.db.desafiovotacao.entities.Associate;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AssociateRepository extends CrudRepository<Associate, Long> {
}
