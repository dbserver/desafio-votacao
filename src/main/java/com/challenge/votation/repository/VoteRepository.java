package com.challenge.votation.repository;

import com.challenge.votation.repository.entity.AgendaEntity;
import com.challenge.votation.repository.entity.VoteEntity;
import org.springframework.data.repository.CrudRepository;

public interface VoteRepository extends CrudRepository<VoteEntity, Long> {
    boolean existsByClientIdAndAgendaEntity(String clientId, AgendaEntity agendaEntity);
}
