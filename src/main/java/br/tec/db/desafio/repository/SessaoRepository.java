package br.tec.db.desafio.repository;

import br.tec.db.desafio.business.domain.Sessao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface SessaoRepository extends JpaRepository<Sessao, Long> {
    @Query(value = "SELECT *\n" +
            "FROM votacao.sessao\n" +
            "WHERE pauta_id =:pautaId",
            nativeQuery = true)
    Sessao findByPautaId(@Param("pautaId") Long pautaId);
}
