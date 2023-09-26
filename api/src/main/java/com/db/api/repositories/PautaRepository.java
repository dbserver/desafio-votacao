package com.db.api.repositories;

import com.db.api.models.Pauta;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PautaRepository extends JpaRepository<Pauta, Long> {
    Optional<Pauta> findByTitulo(String nomePauta);
}
