package br.com.dbserver.voting.repositories;


import br.com.dbserver.voting.models.Associate;
import br.com.dbserver.voting.models.vote.Vote;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;


import java.util.UUID;

@Repository
public interface VoteRepository extends JpaRepository<Vote, UUID> {

    boolean existsByAssociate(Associate associate);
}
