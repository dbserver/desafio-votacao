package com.db.desafiovotacao.api.service;

import com.db.desafiovotacao.api.exception.AgendaNotFoundException;
import com.db.desafiovotacao.api.record.SessionRecord;
import java.time.Duration;
import java.util.UUID;

public interface OpenSessionServiceInterface {
    public SessionRecord openSession(UUID agendaId, Duration duration) throws AgendaNotFoundException;
}
