package com.db.desafiovotacao.api.service;

import com.db.desafiovotacao.api.entity.Agenda;
import com.db.desafiovotacao.api.repository.AgendaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreateAgendaService implements CreateAgendaServiceInterface {

    private final AgendaRepository agendaRepository;

    @Autowired
    public CreateAgendaService(AgendaRepository agendaRepository) {
        this.agendaRepository = agendaRepository;
    }

    public Agenda createAgenda(String name) {
        Agenda agenda = new Agenda();
        agenda.setName(name);
        return agendaRepository.save(agenda);
    }

}
