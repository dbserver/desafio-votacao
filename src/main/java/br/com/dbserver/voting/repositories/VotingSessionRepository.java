package br.com.dbserver.voting.repositories;

import br.com.dbserver.voting.dtos.vote.ResultOfTheVoteDTO;
import br.com.dbserver.voting.enums.StatusVotingSessionEnum;
import br.com.dbserver.voting.models.VotingSession;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface VotingSessionRepository extends JpaRepository<VotingSession, Integer> {

    boolean existsByScheduleIdAndStatus(Integer id, StatusVotingSessionEnum status);

    @Query("SELECT new br.com.dbserver.voting.dtos.vote.ResultOfTheVoteDTO(" +
            "vs.id, " +
            "vs.start, " +
            "vs.end, " +
            "vs.schedule, " +
            "SUM(CASE WHEN v.typeVote = 'SIM' THEN 1 ELSE 0 END), " +
            "SUM(CASE WHEN v.typeVote = 'NAO' THEN 1 ELSE 0 END), " +
            "vs.status) " +
            "FROM VotingSession vs " +
            "JOIN Vote v ON vs.id = v.votingSession.id " +
            "JOIN Schedule s ON s.id = v.schedule.id " +
            "WHERE vs.status = 'OPEN' " +
            "GROUP BY vs.id")
    List<ResultOfTheVoteDTO> voteProgress();

    @Query("SELECT new br.com.dbserver.voting.dtos.vote.ResultOfTheVoteDTO(" +
            "vs.id, " +
            "vs.start, " +
            "vs.end, " +
            "vs.schedule, " +
            "SUM(CASE WHEN v.typeVote = 'SIM' THEN 1 ELSE 0 END), " +
            "SUM(CASE WHEN v.typeVote = 'NAO' THEN 1 ELSE 0 END), " +
            "vs.status) " +
            "FROM VotingSession vs " +
            "JOIN Vote v ON vs.id = v.votingSession.id " +
            "JOIN Schedule s ON s.id = vs.schedule.id " +
            "WHERE vs.id = ?1 " +
            "GROUP BY vs.id")
    Optional<ResultOfTheVoteDTO> voteProgressByIdSession(Integer idVoteSession);

}
