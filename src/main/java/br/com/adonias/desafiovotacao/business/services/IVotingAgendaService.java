package br.com.adonias.desafiovotacao.business.services;

import br.com.adonias.desafiovotacao.entities.VotingAgenda;

import java.util.List;
import java.util.Optional;

public interface IVotingAgendaService {
    Optional<VotingAgenda> getVotingAgendaById(Long id);

    List<VotingAgenda> getAllVotingAgendas();

    VotingAgenda save(VotingAgenda votingAgenda);

    void delete(Long id);
}
