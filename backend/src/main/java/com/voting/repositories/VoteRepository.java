package com.voting.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.voting.entities.Vote;

@Repository
public interface VoteRepository extends JpaRepository<Vote, Integer> {
}
