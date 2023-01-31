package br.com.adonias.desafiovotacao.repositories;

import br.com.adonias.desafiovotacao.entities.Vote;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IVoteRepository extends CrudRepository<Vote, Long> {

    List<Vote> findAll();

    List<Vote> findAllByAgendaId(Long agendaId);

    boolean existsVoteByAgendaIdAndCpfAssociate(Long agendaId, String cpf);
}
