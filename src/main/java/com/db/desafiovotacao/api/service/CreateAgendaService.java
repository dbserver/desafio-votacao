package com.db.desafiovotacao.api.service;

import com.db.desafiovotacao.api.entity.Agenda;
import com.db.desafiovotacao.api.record.AgendaRecord;
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

    public AgendaRecord save(AgendaRecord agendaRecord) {
        Agenda agenda=agendaRepository.save(new Agenda(agendaRecord.id(), agendaRecord.votes()));
        return new AgendaRecord(agenda.getId(), agenda.getVotes());
    }

}
