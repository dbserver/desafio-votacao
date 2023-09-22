package com.desafio.votacao.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.desafio.votacao.entity.Votacao;

import jakarta.transaction.Transactional;

@Repository
public interface VotacaoRepository extends JpaRepository<Votacao, Long>{

	@Query(" FROM Votacao WHERE ativo = true AND dthFim <= now() ")
	List<Votacao> findByAtivoExpirada();
	

	@Modifying
	@Transactional
	@Query("UPDATE Votacao SET ativo = false WHERE ativo = true AND dthFim <= now() ")
	void inativarVotacaoVencida();
}
