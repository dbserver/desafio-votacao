package com.db.desafiovotacao.api.record;

import com.db.desafiovotacao.api.entity.Vote;

import java.util.List;
import java.util.UUID;

public record OpenSessionAgendaRecord (UUID id, String name, List<Vote> votes) {
}
