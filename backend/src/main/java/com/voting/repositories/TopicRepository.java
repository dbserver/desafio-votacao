package com.voting.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.voting.entities.Topic;

@Repository
public interface TopicRepository extends JpaRepository<Topic, Integer> {
}
