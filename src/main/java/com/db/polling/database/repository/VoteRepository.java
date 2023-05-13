package com.db.polling.database.repository;

import com.db.polling.database.entity.VoteEntity;
import com.db.polling.domain.enumeration.VoteEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface VoteRepository extends JpaRepository<VoteEntity, Long> {

  @Query("SELECT COUNT(v) FROM VoteEntity v WHERE v.votingSessionEntity.votingSessionId = :votingSessionId AND v.vote = :vote")
  Long countByVotingSessionIdAndVote(@Param("votingSessionId")Long votingSessionId, @Param("vote") VoteEnum vote);


  @Query(value = "SELECT COUNT(v) > 0 FROM VoteEntity v WHERE "
      + " v.votingSessionEntity.votingSessionId = :votingSessionId"
      + " AND v.associateEntity.associateId = :associateId")
  boolean existsByVotingSessionIdAndAssociateId(@Param("votingSessionId")Long votingSessionId, @Param("associateId") Long associateId);

}