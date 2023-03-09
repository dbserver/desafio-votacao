package com.dbserver.repository;

import com.dbserver.model.entity.Vote;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface VoteRepository extends MongoRepository<Vote, String> {
    List<Vote> findAllByIdVoting(String idVoting);
    boolean existsByCpfAndIdVoting(String cpf, String idVoting);
}
