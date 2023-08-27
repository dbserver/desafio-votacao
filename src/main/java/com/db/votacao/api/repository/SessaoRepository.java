package com.db.votacao.api.repository;

import com.db.votacao.api.model.Sessao;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Repository
public interface SessaoRepository extends CrudRepository<Sessao, UUID> {

    @Query("SELECT s FROM Sessao s " +
            "WHERE (:dataCriacao IS NULL OR s.dataCriacao >= :dataCriacao) " +
            "AND (:inicioSessao IS NULL OR s.inicioSessao >= :inicioSessao) " +
            "AND (:finalSessao IS NULL OR s.finalSessao <= :finalSessao)")
    List<Sessao> findSessoesByFiltros(@Param("dataCriacao") LocalDateTime dataCriacao,
                                      @Param("inicioSessao") LocalDateTime inicioSessao,
                                      @Param("finalSessao") LocalDateTime finalSessao);
}
