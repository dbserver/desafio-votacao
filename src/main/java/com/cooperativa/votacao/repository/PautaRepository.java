package com.cooperativa.votacao.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cooperativa.votacao.entity.PautaEntity;

@Repository
public interface PautaRepository extends JpaRepository<PautaEntity,Integer> {

}
