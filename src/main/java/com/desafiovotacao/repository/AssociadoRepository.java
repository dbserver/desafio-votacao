package com.desafiovotacao.repository;

import com.desafiovotacao.domain.Associado;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AssociadoRepository extends JpaRepository<Associado, String> {

    Page<Associado> findAll(Pageable pageable);

}
