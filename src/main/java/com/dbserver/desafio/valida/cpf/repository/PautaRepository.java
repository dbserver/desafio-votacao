package com.dbserver.desafio.valida.cpf.repository;

import com.dbserver.desafio.valida.cpf.repository.entity.PautaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PautaRepository extends JpaRepository<PautaEntity, Integer> {
}
