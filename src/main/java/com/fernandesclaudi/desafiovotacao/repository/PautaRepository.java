package com.fernandesclaudi.desafiovotacao.repository;

import com.fernandesclaudi.desafiovotacao.model.Pauta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PautaRepository extends JpaRepository<Pauta, Long> {
}
