package br.tec.db.desafio.repository;

import br.tec.db.desafio.business.domain.Sessao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface SessaoRepository extends JpaRepository<Sessao, Long> {

    Sessao findByPautaId(@Param("pautaId") Long pautaId);
}
