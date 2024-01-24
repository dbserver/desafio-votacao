package com.db.desafiovotacao.api.converters;

import com.db.desafiovotacao.api.entity.Session;
import com.db.desafiovotacao.api.record.OpenSessionAgendaRecord;
import com.db.desafiovotacao.api.record.OpenSessionRecord;
import org.springframework.stereotype.Component;

@Component
public class OpenSessionRecordConverter {

    public OpenSessionRecord toOpenSessionRecord(Session session){
        return new OpenSessionRecord(session.getId(), session.getDataBegin(), session.getDataEnd(), new OpenSessionAgendaRecord(session.getAgenda().getId(),session.getAgenda().getName(),session.getAgenda().getVotes()), session.getStatus());
    }
}
