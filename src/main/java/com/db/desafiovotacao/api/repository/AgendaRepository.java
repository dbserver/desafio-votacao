package com.db.desafiovotacao.api.repository;

import com.db.desafiovotacao.api.entity.Agenda;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface AgendaRepository extends JpaRepository<Agenda, UUID> {
}
