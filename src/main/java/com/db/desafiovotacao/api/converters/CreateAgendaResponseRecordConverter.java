package com.db.desafiovotacao.api.converters;

import com.db.desafiovotacao.api.entity.Agenda;
import com.db.desafiovotacao.api.record.CreateAgendaResponseRecord;
import org.springframework.stereotype.Component;

@Component
public class CreateAgendaResponseRecordConverter {

    public CreateAgendaResponseRecord toCreateAgendaResponseRecord(Agenda agenda){
        return new CreateAgendaResponseRecord(agenda.getId(),agenda.getName());
    }
}
