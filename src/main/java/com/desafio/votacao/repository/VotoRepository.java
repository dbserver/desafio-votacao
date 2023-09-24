package com.desafio.votacao.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.desafio.votacao.entity.Pauta;
import com.desafio.votacao.entity.Voto;

@Repository
public interface VotoRepository extends JpaRepository<Voto, Long>{
 
	long countByVotoAndPauta(boolean voto, Pauta pauta);
}
