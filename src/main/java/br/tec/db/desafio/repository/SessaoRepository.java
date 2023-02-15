package br.tec.db.desafio.repository;

import br.tec.db.desafio.business.entity.Pauta;
import br.tec.db.desafio.business.entity.Sessao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface SessaoRepository extends JpaRepository<Sessao, Long> {
    Sessao findSessaoByPauta(@Param("pauta") Pauta pauta);
}
