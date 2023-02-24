package br.tec.db.desafio.repository;

import br.tec.db.desafio.business.domain.Associado;
import br.tec.db.desafio.business.domain.AssociadoSessao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AssociadoSessaoRepository extends JpaRepository<AssociadoSessao, Long> {

    @Query(value = "select associados_id from " +
            "votacao_dev.associado_sessoes " +
            "where associados_id= :associadoId and " +
            "sessoes_id= :sessaoId", nativeQuery = true)
    Long findByAssociadoIdAndSessaoId(
            @Param("associadoId") Long associadoId,
            @Param("sessaoId") Long sessaoId);



}
