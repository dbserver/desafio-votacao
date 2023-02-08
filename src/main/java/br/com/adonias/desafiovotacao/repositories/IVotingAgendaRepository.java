package br.com.adonias.desafiovotacao.repositories;

import br.com.adonias.desafiovotacao.entities.VotingAgenda;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IVotingAgendaRepository extends CrudRepository<VotingAgenda, Long> {

    List<VotingAgenda> findAll();
}
