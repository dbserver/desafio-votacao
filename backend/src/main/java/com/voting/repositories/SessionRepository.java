package com.voting.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.voting.entities.Session;
import com.voting.entities.Topic;

@Repository
public interface SessionRepository extends JpaRepository<Session, Integer> {
	Optional<Session> findByTopic(Topic topic);
}
