package com.br.dbserver.votacao.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.br.dbserver.votacao.model.Pauta;

@Repository
public interface PautaRepository  extends JpaRepository<Pauta, Long> {

	@Query(value = "UPDATE `votacao`.`pauta` SET `resultado_votacao`= ?1 WHERE  `id`= ?2", nativeQuery = true)
	void atualizarResultado(long id, String resultado);

}
