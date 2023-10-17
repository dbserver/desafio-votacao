package com.challenge.repository;

import com.challenge.model.StaveSession;
import com.challenge.model.Vote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VoteRepository extends JpaRepository<Vote, Long> {
    @Query("SELECT COUNT(v) > 0 FROM Vote v WHERE v.associate.id = :associateId AND v.staveSession.id = :staveSessionId")
    boolean existsByAssociateAndSession(@Param("associateId") Long associateId, @Param("staveSessionId") Long staveSessionId);

    List<Vote> findByStaveSession(StaveSession StaveSession);
}
