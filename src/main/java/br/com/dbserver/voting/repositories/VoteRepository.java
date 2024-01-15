package br.com.dbserver.voting.repositories;


import br.com.dbserver.voting.models.Associate;
import br.com.dbserver.voting.models.Schedule;
import br.com.dbserver.voting.models.Vote;
import br.com.dbserver.voting.models.VotingSession;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;


@Repository
public interface VoteRepository extends JpaRepository<Vote, Integer> {

    boolean existsByAssociateAndVotingSession(Associate associate, VotingSession votingSession);
}
