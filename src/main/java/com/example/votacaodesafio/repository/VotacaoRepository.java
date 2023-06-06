package com.example.votacaodesafio.repository;

import com.example.votacaodesafio.domain.entity.Votacao;
import com.example.votacaodesafio.domain.enums.VotoEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.HashMap;

@Repository
public interface VotacaoRepository  extends JpaRepository<Votacao, Long> {

    @Query("SELECT v.vote, COUNT(v.id) FROM Votacao v WHERE v.sessaoVotacao.id = :sessaoId group by v.vote")
    HashMap<String, Long> countByVotingSessionIdAndVote(@Param("sessaoId") Long sessaoId);

    @Query(value = "SELECT COUNT(v) > 0 FROM Votacao v WHERE v.sessaoVotacao.id = :sessaoId AND v.assosciado.id = :idAssociado")
    boolean existsByVotingSessionIdAndAssociateId(@Param("sessaoId") Long sessaoId, @Param("idAssociado") Long idAssociado);
}
