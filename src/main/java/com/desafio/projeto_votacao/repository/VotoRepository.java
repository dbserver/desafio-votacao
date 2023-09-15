package com.desafio.projeto_votacao.repository;

import com.desafio.projeto_votacao.entity.Voto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VotoRepository extends JpaRepository<Voto, Long> {

}
