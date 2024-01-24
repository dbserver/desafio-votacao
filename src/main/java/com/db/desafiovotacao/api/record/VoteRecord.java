package com.db.desafiovotacao.api.record;

import java.util.UUID;

public record VoteRecord(UUID id, VoteMemberRecord voteMemberRecord, VoteAgendaRecord voteAgendaRecord, Boolean voted) {
}
