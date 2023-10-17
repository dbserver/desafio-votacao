package com.challenge.repository;

import com.challenge.model.StaveSession;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StaveSessionRepository extends JpaRepository<StaveSession, Long> {
    @Query("SELECT v FROM StaveSession v WHERE v.stave.id = :staveId")
    Optional<StaveSession> findByStaveId(@Param("staveId") Long staveId);
}
