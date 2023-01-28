package br.com.occ.desafiovotacao.v1.repository;

import br.com.occ.desafiovotacao.v1.model.Sessao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface SessaoRepository extends JpaRepository<Sessao, Long> {

    @Query("select a from Sessao a where a.dataFim < :data")
    List<Sessao> findAllSessoesAtivas(LocalDateTime data);
}
