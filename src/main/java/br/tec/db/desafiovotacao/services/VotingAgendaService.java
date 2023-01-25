package br.tec.db.desafiovotacao.services;

import br.tec.db.desafiovotacao.repositories.VotingAgendaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VotingAgendaService {

    @Autowired
    private VotingAgendaRepository repository;
}
