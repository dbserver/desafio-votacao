package com.db.desafiovotacao.api.service;

import com.db.desafiovotacao.api.exception.AgendaNotFoundException;

import java.util.UUID;

public interface CountVotesServiceInterface {
    public String getResultVoting(UUID agendaId) throws AgendaNotFoundException;
}
