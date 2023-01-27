package br.com.occ.desafiovotacao.v1.repository;

import br.com.occ.desafiovotacao.v1.model.Pauta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;


@Repository
public interface PautaRepository extends JpaRepository<Pauta, Long> {

    @Query("select a from Pauta a where a.sessao.dataFim > :data")
    List<Pauta> findAllPautasAtivas(LocalDateTime data);
}
