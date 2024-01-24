package com.db.desafiovotacao.api.service;

import com.db.desafiovotacao.api.converters.CreateAgendaResponseRecordConverter;
import com.db.desafiovotacao.api.entity.Agenda;
import com.db.desafiovotacao.api.record.CreateAgendaResponseRecord;
import com.db.desafiovotacao.api.repository.AgendaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreateAgendaService implements CreateAgendaServiceInterface {

    private final AgendaRepository agendaRepository;

    private final CreateAgendaResponseRecordConverter createAgendaResponseRecordConverter;

    @Autowired
    public CreateAgendaService(AgendaRepository agendaRepository,CreateAgendaResponseRecordConverter createAgendaResponseRecordConverter) {
        this.agendaRepository = agendaRepository;
        this.createAgendaResponseRecordConverter = createAgendaResponseRecordConverter;
    }

    public CreateAgendaResponseRecord createAgenda(String name) {
        Agenda agenda = new Agenda();
        agenda.setName(name);
        agenda = agendaRepository.save(agenda);
        return createAgendaResponseRecordConverter.toCreateAgendaResponseRecord(agenda);
    }

}
