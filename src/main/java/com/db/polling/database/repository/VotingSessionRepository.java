package com.db.polling.database.repository;

import com.db.polling.database.entity.VotingSessionEntity;
import java.time.LocalDateTime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface VotingSessionRepository extends JpaRepository<VotingSessionEntity, Long> {

  boolean existsByClosingTimeLessThanEqualAndAgendaEntityAgendaId(LocalDateTime now, Long agendaId);

}