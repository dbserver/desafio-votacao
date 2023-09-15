package com.desafio.projeto_votacao.repository;

import com.desafio.projeto_votacao.entity.Associado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AssociadoRepository extends JpaRepository<Associado, Long> {

}
