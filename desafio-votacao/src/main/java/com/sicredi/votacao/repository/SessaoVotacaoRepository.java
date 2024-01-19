package com.sicredi.votacao.repository;

import com.sicredi.votacao.entity.SessaoVotacao;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Fabio Moraes
 */
public interface SessaoVotacaoRepository extends JpaRepository<SessaoVotacao, Long> {
    
}
