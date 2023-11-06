package com.voting.repositories;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.voting.entities.Session;
import com.voting.entities.Topic;

@Repository
public interface SessionRepository extends JpaRepository<Session, Integer> {
	Optional<Session> findByTopic(Topic topic);

	@Query("select s from Session s where s.closingDate > :date")
	List<Session> findOpenSessions(Instant date);

	@Query("select s from Session s where s.closingDate <= :date")
	List<Session> findClosedSessions(Instant date);
}
