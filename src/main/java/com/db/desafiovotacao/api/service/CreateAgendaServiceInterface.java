package com.db.desafiovotacao.api.service;

import com.db.desafiovotacao.api.entity.Agenda;
import com.db.desafiovotacao.api.record.CreateAgendaResponseRecord;

public interface CreateAgendaServiceInterface {
    public CreateAgendaResponseRecord createAgenda(String name);
}
