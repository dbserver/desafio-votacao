package br.tec.db.desafio.repository;

import br.tec.db.desafio.business.entity.Pauta;
import br.tec.db.desafio.business.entity.Sessao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface SessaoRepository extends JpaRepository<Sessao, Long> {
//    @Query(value = "SELECT *\n" +
//            "FROM VOTACAO.SESSAO\n" +
//            "WHERE assunto =:assuntoPauta",
//            nativeQuery = true)
//    Sessao findByAssuntoPauta(@Param("assuntoPauta") String assuntoPauta);
}
