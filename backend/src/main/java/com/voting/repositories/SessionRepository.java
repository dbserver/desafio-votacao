package com.voting.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.voting.entities.Session;

@Repository
public interface SessionRepository extends JpaRepository<Session, Integer> {
}
