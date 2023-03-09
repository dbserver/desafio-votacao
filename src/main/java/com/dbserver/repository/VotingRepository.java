package com.dbserver.repository;

import com.dbserver.model.entity.Voting;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface VotingRepository extends MongoRepository<Voting, String> {
    Optional<Voting> findByIdAgenda(String idAgenda);
    boolean existsByIdAgenda(String idAgenda);
}
