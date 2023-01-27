package br.com.adonias.desafiovotacao.business.services;

import br.com.adonias.desafiovotacao.entities.Vote;

import java.util.List;
import java.util.Optional;

public interface IVoteService {
    Optional<Vote> getVoteById(Long id);

    List<Vote> getAllVotes();

    Vote save(Vote associate);

    void delete(Long id);
}
