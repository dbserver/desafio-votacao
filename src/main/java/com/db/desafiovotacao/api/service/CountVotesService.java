package com.db.desafiovotacao.api.service;

import com.db.desafiovotacao.api.entity.Agenda;
import com.db.desafiovotacao.api.entity.Vote;
import com.db.desafiovotacao.api.exception.AgendaNotFoundException;
import com.db.desafiovotacao.api.repository.AgendaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CountVotesService implements CountVotesServiceInterface {

    private final AgendaRepository agendaRepository;

    @Autowired
    public CountVotesService(AgendaRepository agendaRepository) {
        this.agendaRepository = agendaRepository;
    }

    public String getResultVoting(UUID agendaId) throws AgendaNotFoundException {
        Agenda agenda = agendaRepository.findById(agendaId)
                .orElseThrow(() -> new AgendaNotFoundException("Agenda not found"));

        int positiveVotes = countPositiveVotes(agenda);
        int negativeVotes = countNegativeVotes(agenda);

        if (positiveVotes > negativeVotes) {
            return "Agenda Approved";
        } else if (negativeVotes > positiveVotes) {
            return "Agenda Rejected";
        } else {
            return "Deuce";
        }
    }

    private int countPositiveVotes(Agenda agenda) {
        return (int) agenda.getVotes().stream().filter(Vote::getVoted).count();
    }

    private int countNegativeVotes(Agenda agenda) {
        return (int) agenda.getVotes().stream().filter(v -> !v.getVoted()).count();
    }
}
