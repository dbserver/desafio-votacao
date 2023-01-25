package br.tec.db.desafiovotacao.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VotingAgendaRepository extends CrudRepository<VoteRepository, Long> {
}
