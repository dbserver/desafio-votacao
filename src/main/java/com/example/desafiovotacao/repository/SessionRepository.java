package com.example.desafiovotacao.repository;

import com.example.desafiovotacao.dto.SessionReturnDTO;
import com.example.desafiovotacao.entity.SessionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SessionRepository extends JpaRepository<SessionEntity, Integer> {
    @Query("SELECT se " +
            "FROM SessionEntity se " +
            "WHERE se.ruling.id = :rulingId " +
            "AND se.creationDate = (SELECT MAX(se2.creationDate) FROM SessionEntity se2 WHERE se2.ruling.id = se.ruling.id)")
    Optional<SessionEntity> findLatestByRulingId(Integer rulingId);

    @Query("SELECT NEW com.example.desafiovotacao.dto.SessionReturnDTO( " +
            " se.id, " +
            " se.ruling.title, " +
            " se.ruling.id, " +
            " se.duration," +
            " FORMAT(se.creationDate, 'dd/MM/yyyy')" +
            ") " +
            "FROM SessionEntity se")
    List<SessionReturnDTO> listAllReturns();

    @Query("SELECT NEW com.example.desafiovotacao.dto.SessionReturnDTO( " +
            " se.id, " +
            " se.ruling.title, " +
            " se.ruling.id, " +
            " se.duration," +
            " FORMAT(se.creationDate, 'dd/MM/yyyy')" +
            ") " +
            "FROM SessionEntity se " +
            "WHERE se.id = :id")
    Optional<SessionReturnDTO> findReturnById(Integer id);
}
