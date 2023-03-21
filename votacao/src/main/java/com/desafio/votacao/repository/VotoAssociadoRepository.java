package com.desafio.votacao.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.desafio.votacao.entity.Associado;

@Repository
public interface VotoAssociadoRepository extends JpaRepository<Associado, Long> {

	Optional<Associado> findById(Long cpf);
}
