package com.example.desafiovotacao.repository;

import com.example.desafiovotacao.entity.VoteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VoteRepository extends JpaRepository<VoteEntity, Integer> {
    @Query(value = "SELECT ve.associate.cpf FROM VoteEntity ve WHERE ve.associate.cpf = :cpf AND ve.session.id = :sessionId")
    Optional<VoteEntity> findByCpfAndSession(String cpf, Integer sessionId);
}
