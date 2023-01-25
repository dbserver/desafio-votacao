package br.tec.db.desafiovotacao.repositories;

import br.tec.db.desafiovotacao.entities.Vote;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VoteRepository extends CrudRepository<Vote, Long> {
}
