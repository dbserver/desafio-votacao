package com.challenge.repository;

import com.challenge.model.Stave;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StaveRepository extends JpaRepository<Stave, Long> {

}
