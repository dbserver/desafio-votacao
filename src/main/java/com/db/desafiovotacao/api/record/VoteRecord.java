package com.db.desafiovotacao.api.record;

import com.db.desafiovotacao.api.entity.Agenda;
import com.db.desafiovotacao.api.entity.Member;

import java.util.UUID;

public record VoteRecord(UUID id, MemberRecord memberRecord, AgendaRecord agendaRecord, Boolean voted) {
}
