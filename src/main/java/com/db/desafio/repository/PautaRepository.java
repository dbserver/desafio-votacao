package com.db.desafio.repository;


import com.db.desafio.entity.Pauta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PautaRepository extends JpaRepository<Pauta,Long> {
    Optional<Pauta> findByTitulo(String titulo);

}
