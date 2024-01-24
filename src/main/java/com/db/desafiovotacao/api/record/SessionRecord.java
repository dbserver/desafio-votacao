package com.db.desafiovotacao.api.record;

import com.db.desafiovotacao.api.domain.Status;
import com.db.desafiovotacao.api.entity.Agenda;

import java.time.LocalDateTime;
import java.util.UUID;

public record SessionRecord(UUID id, LocalDateTime dataBegin, LocalDateTime dataEnd, AgendaRecord agendaRecord, Status status) {
}
