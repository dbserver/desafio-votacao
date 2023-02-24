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


    @Query(value = "SELECT A.associadoId FROM " +
            "AssociadoSessao A " +
            "WHERE A.associadoId IN :associadoId AND " +
            "A.sessaoId IN :sessaoId")
    Long findByAssociadoIdAndSessaoId(
            @Param("associadoId") Long associadoId,
            @Param("sessaoId") Long sessaoId);

}
