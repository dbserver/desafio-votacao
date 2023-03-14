package com.dbserver.repository;

import com.dbserver.model.entity.VotingSession;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface VotingSessionRepository extends MongoRepository<VotingSession, String> {
    Optional<VotingSession> findByIdAgenda(String idAgenda);
    boolean existsByIdAgenda(String idAgenda);
}
