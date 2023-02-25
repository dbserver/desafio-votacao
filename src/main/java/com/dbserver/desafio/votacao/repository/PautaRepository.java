package com.dbserver.desafio.votacao.repository;

import com.dbserver.desafio.votacao.repository.entity.PautaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PautaRepository extends JpaRepository<PautaEntity, Integer> {
}
