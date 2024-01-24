package com.db.desafiovotacao.api.service;

import com.db.desafiovotacao.api.entity.Vote;
import com.db.desafiovotacao.api.exception.AgendaNotFoundException;
import com.db.desafiovotacao.api.exception.MemberNotFoundException;
import com.db.desafiovotacao.api.exception.OperationNotPermittedException;
import com.db.desafiovotacao.api.record.VoteRecord;

import java.util.UUID;

public interface VoteServiceInterface {

    public VoteRecord vote(UUID memberId, UUID agendaId, Boolean voted) throws MemberNotFoundException, AgendaNotFoundException, OperationNotPermittedException;
}
