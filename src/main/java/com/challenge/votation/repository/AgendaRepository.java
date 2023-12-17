package com.challenge.votation.repository;

import com.challenge.votation.repository.entity.AgendaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AgendaRepository extends JpaRepository<AgendaEntity, Long> {
}
