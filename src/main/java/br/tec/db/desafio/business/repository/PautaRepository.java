package br.tec.db.desafio.business.repository;

import br.tec.db.desafio.business.entity.Pauta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PautaRepository extends JpaRepository<Pauta, Long> {
    Pauta findPautaByAssunto(@Param("assunto") String assunto);
}
