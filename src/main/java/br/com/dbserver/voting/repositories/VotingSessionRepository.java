package br.com.dbserver.voting.repositories;

import br.com.dbserver.voting.dtos.vote.ResultOfTheVoteDTO;
import br.com.dbserver.voting.enums.StatusVotingSessionEnum;
import br.com.dbserver.voting.models.VotingSession;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface VotingSessionRepository extends JpaRepository<VotingSession, UUID> {

    boolean existsByScheduleIdAndStatus(UUID id, StatusVotingSessionEnum status);

    @Query("SELECT new br.com.dbserver.voting.dtos.vote.ResultOfTheVoteDTO(" +
            "vs.id, " +
            "vs.start, " +
            "vs.end, " +
            "vs.schedule, " +
            "SUM(CASE WHEN v.typeVote = 'SIM' THEN 1 ELSE 0 END), " +
            "SUM(CASE WHEN v.typeVote = 'NAO' THEN 1 ELSE 0 END), " +
            "vs.status) " +
            "FROM VotingSession vs JOIN Vote v ON vs.schedule.id = v.schedule.id GROUP BY vs.id")
    Optional<ResultOfTheVoteDTO> voteProgress();
}
