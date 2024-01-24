package com.db.desafiovotacao.api.record;

import com.db.desafiovotacao.api.domain.Status;

import java.time.LocalDateTime;
import java.util.UUID;

public record OpenSessionRecord(UUID id, LocalDateTime dataBegin, LocalDateTime dataEnd, OpenSessionAgendaRecord openSessionAgendaRecord, Status status) {
}
