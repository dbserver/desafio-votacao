package com.br.dbserver.votacao.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.br.dbserver.votacao.model.Voto;

public interface VotoRepository   extends JpaRepository<Voto, Long> {

	@Query(value = "SELECT * FROM `votacao`.`voto` WHERE `pauta_id`= ?1", nativeQuery = true)
	List<Voto> findByPautaId(long pautaId);


}
