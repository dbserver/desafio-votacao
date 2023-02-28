package br.tec.db.desafio.repository;

import br.tec.db.desafio.business.domain.Pauta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PautaRepository extends JpaRepository<Pauta, Long> {
    Pauta findPautaByAssunto(@Param("assunto") String assunto);
    @Query(value = "SELECT P.id FROM " +
            "Pauta P " +
            "WHERE P.assunto IN :assunto")
    Long findIdByAssunto(@Param("assunto") String assunto);
}
