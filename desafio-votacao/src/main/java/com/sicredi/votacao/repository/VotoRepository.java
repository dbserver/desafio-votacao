package com.sicredi.votacao.repository;

import com.sicredi.votacao.entity.Voto;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Fabio Moraes
 */
public interface VotoRepository extends JpaRepository<Voto, Long> {
    
    boolean existsByCpfAndPautaId(String cpf, Long pautaId);
    
    long countByVotoAndPautaId(Boolean voto, Long pautaId);
    
}
