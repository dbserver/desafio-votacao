package com.desafio.votacao.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.desafio.votacao.entity.Associado;

@Repository
public interface AssociadoRepository extends JpaRepository<Associado, Long>{

}
