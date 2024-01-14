package com.cooperativa.votacao.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cooperativa.votacao.entity.PautaEntity;
import com.cooperativa.votacao.entity.StatusSessaoEntity;

@Repository
public interface PautaRepository extends JpaRepository<PautaEntity,Integer> {
	
	List<PautaEntity> findByStatusSessao(StatusSessaoEntity statusSessaoEntity); 

}
