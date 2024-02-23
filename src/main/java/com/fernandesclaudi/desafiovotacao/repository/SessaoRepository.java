package com.fernandesclaudi.desafiovotacao.repository;

import com.fernandesclaudi.desafiovotacao.model.Sessao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface SessaoRepository extends JpaRepository<Sessao, Long> {
    List<Sessao> findByDataFimAfter(LocalDateTime dataFim);
    List<Sessao> findByDataFimAfterAndPauta_Id(LocalDateTime dataFim, Long idPauta);
}
