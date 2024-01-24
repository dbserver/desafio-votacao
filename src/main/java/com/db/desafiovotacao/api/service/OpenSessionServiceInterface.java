package com.db.desafiovotacao.api.service;

import com.db.desafiovotacao.api.entity.Session;
import com.db.desafiovotacao.api.exception.AgendaNotFoundException;
import com.db.desafiovotacao.api.record.OpenSessionRecord;

import java.time.Duration;
import java.util.UUID;

public interface OpenSessionServiceInterface {
    public OpenSessionRecord openSession(UUID agendaId, Duration duration) throws AgendaNotFoundException;
}
