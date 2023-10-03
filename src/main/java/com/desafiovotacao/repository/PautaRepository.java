package com.desafiovotacao.repository;

import com.desafiovotacao.domain.Pauta;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PautaRepository extends JpaRepository<Pauta, String> {

    Page<Pauta> findAll(Pageable pageable);

}
