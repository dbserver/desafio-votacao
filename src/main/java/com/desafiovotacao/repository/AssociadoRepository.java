package com.desafiovotacao.repository;

import com.desafiovotacao.domain.Associado;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AssociadoRepository extends JpaRepository<Associado, String> {

    Page<Associado> findAll(Pageable pageable);

    void deleteByCpf(String cpf);
}
