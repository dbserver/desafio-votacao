package com.db.polling.database.repository;

import com.db.polling.database.entity.AgendaEntity;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AgendaRepository extends JpaRepository<AgendaEntity, Long> {

  Optional<AgendaEntity> findByTitle(String title);
}