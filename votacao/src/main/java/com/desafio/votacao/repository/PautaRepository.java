package com.desafio.votacao.repository;

import java.util.List;

import com.desafio.votacao.entity.Pauta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PautaRepository extends JpaRepository<Pauta, Long> {

	List<Pauta> findByFlFinalizadaFalse();
  
}
