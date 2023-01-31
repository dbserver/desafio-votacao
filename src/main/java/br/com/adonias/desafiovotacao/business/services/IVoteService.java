package br.com.adonias.desafiovotacao.business.services;

import br.com.adonias.desafiovotacao.entities.Vote;

import java.util.List;
import java.util.Optional;

public interface IVoteService {
    Optional<Vote> getVoteById(Long id);

    List<Vote> getAllVotes();

    List<Vote> getVotesByAgendaId(Long agendaId);

    Vote create(Vote vote);

    Vote update(Vote vote);

    void delete(Long id);
}
