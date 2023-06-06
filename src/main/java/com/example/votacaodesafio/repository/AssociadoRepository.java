package com.example.votacaodesafio.repository;

import com.example.votacaodesafio.domain.entity.Assosciado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AssociadoRepository extends JpaRepository<Assosciado, Long> {
}
