package com.desafio.votacao.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.desafio.votacao.entity.Pauta;
import com.desafio.votacao.enums.PautaStatusEnum;

import jakarta.transaction.Transactional;

@Repository
public interface PautaRepository extends JpaRepository<Pauta, Long>{

	@Modifying
	@Transactional
	@Query("UPDATE Pauta SET ativo = false WHERE id in(:id)")
	void inativarPautaVotacaoVencida(@Param("id") List<Long> id);
	
	List<Pauta> findByStatus(PautaStatusEnum status);
}
