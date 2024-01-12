package br.com.dbserver.voting.repositories;

import br.com.dbserver.voting.enums.StatusVotingSession;
import br.com.dbserver.voting.models.VotingSession;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VotingSessionRepository extends JpaRepository<VotingSession, Integer> {

    boolean existsByScheduleIdAndStatus(Integer id, StatusVotingSession status);
}
