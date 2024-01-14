package com.cooperativa.votacao.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cooperativa.votacao.entity.PautaEntity;
import com.cooperativa.votacao.entity.VotoEntity;

@Repository
public interface VotoRepository extends JpaRepository<VotoEntity,Long>{
	
	int countByPautaAndIdAssociado(PautaEntity pautaEntity, Integer idAssociado);

}
