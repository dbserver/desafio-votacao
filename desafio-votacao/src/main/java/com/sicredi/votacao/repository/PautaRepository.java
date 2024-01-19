package com.sicredi.votacao.repository;

import com.sicredi.votacao.entity.Pauta;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Fabio Moraes
 */
public interface PautaRepository extends JpaRepository<Pauta, Long> {
    
}
